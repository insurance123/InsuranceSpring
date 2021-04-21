package com.lti.resource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.lti.dto.AadharCardDto;
import com.lti.dto.CustomerTravelPolicyDto;
import com.lti.dto.CustomerVehiclePolicyDto;
import com.lti.dto.LoginDto;
import com.lti.dto.LoginStatus;
import com.lti.dto.QueryStatus;
import com.lti.dto.RegisterStatus;
import com.lti.dto.RegisterStatus.StatusType;
import com.lti.dto.Status.LoginStatusType;
import com.lti.dto.TravelDto;
import com.lti.dto.VehicleDto;
import com.lti.entity.Admin;
import com.lti.entity.ContactUs;
import com.lti.entity.Customer;
import com.lti.entity.CustomerTravelPolicy;
import com.lti.entity.CustomerVehiclePolicy;
import com.lti.entity.Policy;
import com.lti.entity.Travel;
import com.lti.entity.TravelClaim;
import com.lti.entity.Vehicle;
import com.lti.entity.VehicleClaim;
import com.lti.exception.InsuranceServiceException;
import com.lti.repository.InsuranceRepo;
import com.lti.service.InsuranceService;


//@Controller
@RestController
@CrossOrigin
public class InsuranceController {
	@Autowired
	InsuranceService is;
	
	@Autowired
	InsuranceRepo dao;
	
	
	@PostMapping(value="/registercustomer")
    public RegisterStatus register(@RequestBody Customer customer) {
        try {
            is.addOrUpdateCustomer(customer);
            RegisterStatus status = new RegisterStatus();
            status.setRegisterStatus(StatusType.SUCCESS);
            status.setRegisterMessage("Registration successful!");
            return status;
        }
        catch(InsuranceServiceException e) {
            RegisterStatus status = new RegisterStatus();
            status.setRegisterStatus(StatusType.FAILURE);
            status.setRegisterMessage(e.getMessage());
            return status;
        }
    }
	 @PostMapping(value = "/registeradmin")
	 public void registerAdmin(@RequestBody Admin admin) {
		is.registerAdmin(admin);
		
	}
	 
	 @PostMapping("/aadhar-upload")
//		public RegisterStatus upload(AadharCardDto aadharCardDto) {​​​​​​​
		public RegisterStatus upload(AadharCardDto aadharCardDto) {
				String imageUploadLocation = "d:/uploads/";
		        String fileName = aadharCardDto.getAadharCard().getOriginalFilename();
		        String targetFile = imageUploadLocation + fileName;
		        try {
		        	FileCopyUtils.copy(aadharCardDto.getAadharCard().getInputStream(), new FileOutputStream(targetFile));
		        }
		        catch (IOException e) {
		        	e.printStackTrace();
		        	RegisterStatus rs = new RegisterStatus();
		        	rs.setRegisterStatus(StatusType.FAILURE);
		        	rs.setRegisterMessage(e.getMessage());
		        	return rs;
		        }
		        Customer customer = dao.findCustomerById(aadharCardDto.getUserId());
				customer.setAadharCard(fileName);
				dao.addOrUpdateCustomer(customer);
				
				RegisterStatus status = new RegisterStatus();
				status.setRegisterStatus(StatusType.SUCCESS);
				status.setRegisterMessage("Uploaded!");
				return status;
		}
		 
	
	
	 @PostMapping(value = "/logincustomer")
	   public Customer loginCustomer(@RequestBody LoginDto loginData, HttpSession session) {
		 return is.loginCustomer(loginData.getUserEmail(), loginData.getPassword());
	 }
//	 public LoginStatus loginCustomer(@RequestBody LoginDto loginData) {
//	 	try {
//			 Customer customer = is.loginCustomer(loginData.getUserEmail(),loginData.getPassword());
//			 LoginStatus lstatus = new LoginStatus();
//			 lstatus.setStatus(LoginStatusType.SUCCESS);
//			 lstatus.setMessage("Login Successful");
//			 lstatus.setUserEmail(customer.getUserEmail());
//			 lstatus.setPassword(customer.getPassword());
//			 return lstatus;
//			 
//			
//		} catch (NoResultException nre) {
//			LoginStatus loginStatus = new LoginStatus();
//			loginStatus.setStatus(LoginStatusType.FAILURE);
//			loginStatus.setMessage(nre.getMessage());
//			return loginStatus;
//		}
//	 }
//	 
	 
	 @PostMapping(value = "/loginadmin")
	    public Admin loginAdmin(@RequestBody LoginDto loginData, HttpSession session) {
	        return is.loginAdmin(loginData.getAdminEmail(), loginData.getPassword());
	    }

	 @PostMapping(value="/getQuote")
		public List<Policy> getAQuote(@RequestBody @RequestParam("policyFor") String policyFor) {
			return is.getAQuote(policyFor);
		}

	 @PostMapping(value="buyMotorInsurance")
		public CustomerVehiclePolicy buyMotorInsurance(@RequestBody CustomerVehiclePolicyDto cvp) {
//			cvp.setCustomer(dao.findCustomerById(userId));
//			cvp.setVehicle(dao.findVehicleById(vehicleId));
//			cvp.setPolicy(dao.findPolicyById(policyId));
			return dao.buyMotorInsurance(cvp);
			
		}
	 @PostMapping(value="addVehicle") 
		public Vehicle addVehicle(@RequestBody VehicleDto vehicle) {
//			vehicle.setCustomer(dao.findCustomerById(userId));
			return is.addVehicle(vehicle);
		}
		
		@PostMapping(value="buyTravelInsurance")
		public CustomerTravelPolicy buyTravelInsurance(@RequestBody CustomerTravelPolicyDto ctp) {
//			ctp.setCustomer(dao.findCustomerById(userId));
//			ctp.setPolicy(dao.findPolicyById(policyId));
//			ctp.setTravel(dao.findTravelById(travelId));
			return dao.buyTravelInsurance(ctp);
			
		}
		
		@PostMapping(value="addTravel")
		public Travel addTravel(@RequestBody TravelDto travelDto) {
			//travel.setCustomer(dao.findCustomerById(userId));
			return is.addTravel(travelDto);
		}

	 
		@PostMapping(value="renewMotorInsurance")
		public CustomerVehiclePolicy renewMotorInsurance(@RequestBody CustomerVehiclePolicy cvp, @RequestParam int userId, @RequestParam int policyId, @RequestParam int vehicleId) {
			cvp.setCustomer(dao.findCustomerById(userId));
	        cvp.setVehicle(dao.findVehicleById(vehicleId));
	        cvp.setPolicy(dao.findPolicyById(policyId));
	        
			return is.renewMotorInsurance(cvp);
			
		}

		@PostMapping(value="renewTravelInsurance")
		public CustomerTravelPolicy renewTravelInsurance(@RequestBody CustomerTravelPolicy ctp,@RequestParam int userId, @RequestParam int policyId, @RequestParam int travelId) {
			ctp.setCustomer(dao.findCustomerById(userId));
	        ctp.setTravel(dao.findTravelById(travelId));
	        ctp.setPolicy(dao.findPolicyById(policyId));
			return is.renewTravelInsurance(ctp);
		
		}
		@PostMapping(value="/addnewquery")
        public QueryStatus newQuery(@RequestBody ContactUs contactus) {
            try {
                is.addNewQuery(contactus);
                QueryStatus status= new QueryStatus();
                status.setRegisterMessage("Query sent");
                return status;
            }
            catch(InsuranceServiceException e) {
                QueryStatus status = new QueryStatus();
                status.setRegisterMessage(e.getMessage());
                return status;
            }
        }

	@PostMapping("/fetchquerywithid")
    public String fetchQueryWithQueryId(@RequestParam("queryId") int queryId) {
        return is.fetchQueryWithQueryId(queryId);
    }
	
	 @PostMapping("/fetchallqueries")
    public List<ContactUs> viewAllQueries() {
        return is.viewAllQueries();
    }
	
	@GetMapping(value="/getVehiclePolicies")
	public List<CustomerVehiclePolicy> ViewUserMotorPolicies(@RequestBody @RequestParam("userId") int customerId) {
		return is.ViewUserMotorPolicies(customerId);
	}
	
	@GetMapping(value="/getTravelPolicies")
	public List<CustomerTravelPolicy> viewUserTravelPolicies(@RequestBody @RequestParam("userId") int customerId) {
		return is.viewUserTravelPolicies(customerId);
	}

	public void addMotorClaim(VehicleClaim claim) {
		is.addMotorClaim(claim);
	}

	@GetMapping(value = "getUserById")
	public Customer findCustomerById(@RequestParam int custId) {
		return is.findCustomerById(custId);
	}

	@PostMapping(value = "/addPolicy")
	public void addOrUpdatePolicy(@RequestBody Policy policy) {
		is.addOrUpdatePolicy(policy);
		
	}

	public void addTravelClaim(TravelClaim claim) {
		is.addTravelClaim(claim);
		
	}

	public Policy findPolicyById(int policyId) {
		return is.findPolicyById(policyId);
	}

	public CustomerVehiclePolicy findCustomerVehiclePolicyById(int id) {
		return is.findCustomerVehiclePolicyById(id);
	}

	public CustomerTravelPolicy findCustomerTravelPolicyById(int id) {
		return is.findCustomerTravelPolicyById(id);
	}

	public List<VehicleClaim> viewMotorClaims(int customerId) {
		return is.viewMotorClaims(customerId);
	}

	public List<TravelClaim> viewTravelClaims(int customerId) {
		return is.viewTravelClaims(customerId);
	}

	@GetMapping(value="/viewpendingmotorclaims")
    public List<VehicleClaim> viewPendingMotorClaims() {
        List<VehicleClaim> vehicleclaims = is.viewPendingMotorClaims();
        return vehicleclaims;
    }

 

    @GetMapping(value="/viewpendingtravelclaims")
    public List<TravelClaim> viewPendingTravelClaims() {
        List<TravelClaim> travelclaims =is.viewPendingTravelClaims();
        return travelclaims;
    }
    
	@PutMapping(value="/updateStatus")
	public void updateVehicleClaimStatus(@RequestBody VehicleClaim vc, @RequestParam("status") String status) {
		is.updateVehicleClaimStatus(vc,status);
		
	}

	public VehicleClaim getVehicleClaimById(int claimId) {
		return is.getVehicleClaimById(claimId);
	}

	public void updateTravelClaimStatus(TravelClaim tc) {
		is.updateTravelClaimStatus(tc);
		
	}

	public TravelClaim getTravelClaimById(int claimId) {
		return is.getTravelClaimById(claimId);
	}
	
	@GetMapping(value = "/getPolicyFor")
	public List<Policy> getPolicyFor(@RequestParam String policyFor) {
		return is.getPolicyFor(policyFor);
	}

}

package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.lti.dto.CustomerTravelPolicyDto;
import com.lti.dto.CustomerVehiclePolicyDto;
import com.lti.dto.LoginStatus;
import com.lti.dto.TravelDto;
import com.lti.dto.VehicleDto;
import com.lti.dto.CustomerVehiclePolicyDto;
import com.lti.entity.Admin;
import com.lti.entity.ClaimStatus;
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


@Service
public class InsuranceServiceImpl implements InsuranceService{
	
	@Autowired
	InsuranceRepo ir;
	
	@Autowired
	EmailService emailService;
	
	@Override
    public void addOrUpdateCustomer(Customer customer) {
        if(!ir.isCustomerPresent(customer.getUserEmail())) {
            ir.addOrUpdateCustomer(customer);
            String subject="Registration Confirmation";
            String text="Hi"+customer.getUserName()+"You have succcessfully registered";
            emailService.sendEmailForNewRegistration(customer.getUserEmail(),text,subject);
        }
        else
                throw new InsuranceServiceException("Customer already registred");
    }
	public void addOrUpdateOldCustomer(Customer customer) {
        ir.addOrUpdateCustomer(customer);
    }
	public void registerAdmin(Admin admin) {
		ir.registerAdmin(admin);
		
	}

	public LoginStatus loginCustomer(String userEmail, String password) {
		return ir.loginCustomer(userEmail, password);
	}

	public LoginStatus loginAdmin(String adminEmail, String adminPassword) {
		return ir.loginAdmin(adminEmail, adminPassword);
	}

	public List<Policy> getAQuote(String policyFor) {
		return ir.getAQuote(policyFor);
	}

	public CustomerVehiclePolicy buyMotorInsurance(CustomerVehiclePolicyDto cvp) {
        CustomerVehiclePolicy cp = ir.buyMotorInsurance(cvp);
        Customer customer = ir.findCustomerById(cvp.getCustomerId());
        String subject="Insurance Purchased";
        String text="Hi "+customer.getUserName()+"You have succcessfully purchased the insurance of Rs. " + cvp.getPremiumAmount() + ". Start Date - " + cvp.getStartDate() + ". Expiry Date - " + cvp.getEndDate() + ". Coverage Amount - " + cvp.getCoverageAmount();
        emailService.sendEmailForNewRegistration(customer.getUserEmail(),text,subject);
 
		return cp;

	}
	
	
	public Vehicle addVehicle(VehicleDto vehicle) {
		return ir.addVehicle(vehicle);
	}

	public CustomerTravelPolicy buyTravelInsurance(CustomerTravelPolicyDto ctp) {
		CustomerTravelPolicy cp = ir.buyTravelInsurance(ctp);
        Customer customer = ir.findCustomerById(ctp.getCustomerId());
        String subject="Insurance Purchased";
        String text="Hi "+customer.getUserName()+"You have succcessfully purchased the insurance of Rs. " + ctp.getPremiumAmount() + ". Start Date - " + ctp.getStartDate() + ". Expiry Date - " + ctp.getEndDate() + ". Coverage Amount - " + ctp.getCoverageAmount();
        emailService.sendEmailForNewRegistration(customer.getUserEmail(),text,subject);
 
		return cp;
	}
	public Travel addTravel(TravelDto travelDto) {
		return ir.addTravel(travelDto);
	}

	public CustomerVehiclePolicy renewMotorInsurance(CustomerVehiclePolicy oldCvp, CustomerVehiclePolicy newCvp) {
		 return ir.renewMotorInsurance(oldCvp, newCvp);
		
	}

	public CustomerTravelPolicy renewTravelInsurance(CustomerTravelPolicy ctp) {
		return ir.renewTravelInsurance(ctp);
	}

	@Override
    public void addNewQuery(ContactUs contactUs) {
        
            ir.addNewQuery(contactUs);
            String subject="Your query is received";
            String text="Hi "+contactUs.getUserName()+". We have received your query. Our experts will contact you soon!";
            emailService.sendEmailForNewRegistration(contactUs.getUserEmail(),text,subject);
       
        
    }

	public String fetchQueryWithQueryId(int queryId) {
		return ir.fetchQueryWithQueryId(queryId);
	}

	public List<ContactUs> viewAllQueries() {
		return ir.viewAllQueries();
	}

	public List<CustomerVehiclePolicy> ViewUserMotorPolicies(int customerId) {
		return ir.ViewUserMotorPolicies(customerId);
	}
	
	public List<CustomerTravelPolicy> viewUserTravelPolicies(int customerId) {
		return ir.viewUserTravelPolicies(customerId);
	}

	public VehicleClaim addMotorClaim(VehicleClaim claim) {
		return ir.addMotorClaim(claim);
	}

	public Customer findCustomerById(int custId) {
		return ir.findCustomerById(custId);
	}

	public void addOrUpdatePolicy(Policy policy) {
		ir.addOrUpdatePolicy(policy);
		
	}

	public TravelClaim addTravelClaim(TravelClaim claim) {
		return ir.addTravelClaim(claim);
		
	}

	public Policy findPolicyById(int policyId) {
		return ir.findPolicyById(policyId);
	}

	public CustomerVehiclePolicy findCustomerVehiclePolicyById(int id) {
		return ir.findCustomerVehiclePolicyById(id);
	}

	public CustomerTravelPolicy findCustomerTravelPolicyById(int id) {
		return ir.findCustomerTravelPolicyById(id);
	}

	public List<VehicleClaim> viewMotorClaims(int customerId) {
		return ir.viewMotorClaims(customerId);
	}

	public List<TravelClaim> viewTravelClaims(int customerId) {
		return ir.viewTravelClaims(customerId);
	}

	public List<VehicleClaim> viewPendingMotorClaims() {
		return ir.viewPendingMotorClaims();
	}

	public List<TravelClaim> viewPendingTravelClaims() {
		return ir.viewPendingTravelClaims();
	}

	public void updateVehicleClaimStatus(VehicleClaim vc) {
		Customer customer = vc.getCustomerVehiclePolicy().getCustomer();
		
		VehicleClaim claim = ir.updateVehicleClaimStatus(vc);
		String text = "";
		String subject = "";
		if(claim.getClaimStatus() == ClaimStatus.ACCEPTED) {
			subject="Your Claim has been Accepted!";
	        text="Hi "+customer.getUserName()+" Your Claim Id "+claim.getClaimId()+" has been approved!";
		}
		else {
			subject="Your Claim has been Rejected!";
	        text="Hi "+customer.getUserName()+" Your Claim Id "+claim.getClaimId()+" has been rejected!";
		}
        emailService.sendEmailForNewRegistration(customer.getUserEmail(),text,subject);
		
	}

	
	public void updateTravelClaimStatus(TravelClaim tc) {
		Customer customer = tc.getCustomerTravelPolicy().getCustomer();
		TravelClaim claim = ir.updateTravelClaimStatus(tc);
		String text = "";
		String subject = "";
		if(claim.getClaimStatus() == ClaimStatus.ACCEPTED) {
			subject="Your Claim has been Accepted!";
	        text="Hi "+customer.getUserName()+" Your Claim Id "+claim.getClaimId()+" has been approved!";
		}
		else {
			subject="Your Claim has been Rejected!";
	        text="Hi "+customer.getUserName()+" Your Claim Id "+claim.getClaimId()+" has been rejected!";
		}
        emailService.sendEmailForNewRegistration(customer.getUserEmail(),text,subject);
		
		
	}
	
	public VehicleClaim getVehicleClaimById(int claimId) {
		return ir.getVehicleClaimById(claimId);
	}

	

	public TravelClaim getTravelClaimById(int claimId) {
		return ir.getTravelClaimById(claimId);
	}

	public Vehicle findVehicleById(int vehicleId) {
		return ir.findVehicleById(vehicleId);
	}

	public Travel findTravelById(int travelId) {
		return ir.findTravelById(travelId);
	}

	@Override
	public List<Policy> getPolicyFor(String policyFor) {
		// TODO Auto-generated method stub
		return ir.getPolicyFor(policyFor);
	}

	@Override
	public Vehicle addVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerTravelPolicy buyTravelInsurance(CustomerTravelPolicy ctp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Travel addTravel(Travel travel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Policy> viewAllPolicies() {
		// TODO Auto-generated method stub
		return ir.viewAllPolicies();
	}
	
	//forgot password:
	@Override
    public Customer findCustomerByEmail(String userEmail) {
        return ir.findCustomerByEmail(userEmail);
    }
	
	@Override
    public String updateCustomerPassword(String password, String userEmail) {
        return ir.updateCustomerPassword(password, userEmail);
    }

	public int Generateotp(String userEmail) {
        Customer c=ir.findCustomerByEmail(userEmail);
        int otp =ir.Generateotp();
        String subject="OTP";
        String text="Hi "+" "+ c.getUserName()+" this is your generated otp "+otp;
        emailService.sendEmailForNewRegistration(c.getUserEmail(),text,subject);
        System.out.println("Mail sent");
        return otp;
    }
	@Override
	public List<VehicleClaim> viewAllMotorClaims() {
		// TODO Auto-generated method stub
		return ir.viewAllMotorClaims();
	}
	@Override
	public List<TravelClaim> viewAllTravelClaims() {
		// TODO Auto-generated method stub
		return ir.viewAllTravelClaims();
	}

}

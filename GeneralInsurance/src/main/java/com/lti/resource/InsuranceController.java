package com.lti.resource;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.LoginDto;
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
import com.lti.service.InsuranceService;


//@Controller
@RestController
@CrossOrigin
public class InsuranceController {
	@Autowired
	InsuranceService is;
	
	
	 @PostMapping(value = "/registercustomer")
	    public void addOrUpdateCustomer(@RequestBody Customer customer) {
	        is.addOrUpdateCustomer(customer);
	        
	    }
	 
	 @PostMapping(value = "/registeradmin")
	 public void registerAdmin(@RequestBody Admin admin) {
		is.registerAdmin(admin);
		
	}
	
	
	 @PostMapping(value = "/logincustomer")
	    public Customer loginCustomer(@RequestBody LoginDto loginData, HttpSession session) {
	        return is.loginCustomer(loginData.getUserEmail(), loginData.getPassword());
	    }
	 
	 
	 @PostMapping(value = "/loginadmin")
	    public Admin loginAdmin(@RequestBody LoginDto loginData, HttpSession session) {
	        return is.loginAdmin(loginData.getAdminEmail(), loginData.getPassword());
	    }

	@RequestMapping(value="/getQuote", method=RequestMethod.POST)
	public List<Policy> getAQuote(@RequestBody @RequestParam("policyFor") String policyFor) {
		return is.getAQuote(policyFor);
	}

	public void buyMotorInsurance(Vehicle vehicle) {
		is.buyMotorInsurance(vehicle);
		
	}

	public void buyTravelInsurance(Travel travel) {
		is.buyTravelInsurance(travel);
		
	}

	public void renewMotorInsurance(CustomerVehiclePolicy cvp) {
		is.renewMotorInsurance(cvp);
		
	}

	public void renewTravelInsurance(CustomerTravelPolicy ctp) {
		is.renewTravelInsurance(ctp);
	}

	public void addNewQuery(ContactUs contactUs) {
		is.addNewQuery(contactUs);
		
	}

	public String fetchQueryWithQueryId(int queryId) {
		return is.fetchQueryWithQueryId(queryId);
	}

	public List<ContactUs> viewAllQueries() {
		return is.viewAllQueries();
	}
	
	@RequestMapping(value="/getVehiclePolicies", method=RequestMethod.GET)
	public List<CustomerVehiclePolicy> ViewUserMotorPolicies(@RequestBody @RequestParam("userId") int customerId) {
		return is.ViewUserMotorPolicies(customerId);
	}
	
	@RequestMapping(value="/getTravelPolicies", method=RequestMethod.POST)
	public List<CustomerTravelPolicy> viewUserTravelPolicies(@RequestBody @RequestParam("userId") int customerId) {
		return is.viewUserTravelPolicies(customerId);
	}

	public void addMotorClaim(VehicleClaim claim) {
		is.addMotorClaim(claim);
	}

	public Customer findCustomerById(int custId) {
		return is.findCustomerById(custId);
	}

	public void addOrUpdatePolicy(Policy policy) {
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

	public List<VehicleClaim> viewPendingMotorClaims() {
		return is.viewPendingMotorClaims();
	}

	public List<TravelClaim> viewPendingTravelClaims() {
		return is.viewPendingTravelClaims();
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

}

package com.lti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import com.lti.repository.InsuranceRepo;


@Service
public class InsuranceServiceImpl implements InsuranceService{
	
	@Autowired
	InsuranceRepo ir;
	
	public void addOrUpdateCustomer(Customer customer) {
		ir.addOrUpdateCustomer(customer);
	}

	public void registerAdmin(Admin admin) {
		ir.registerAdmin(admin);
		
	}

	public Customer loginCustomer(String userEmail, String password) {
		return ir.loginCustomer(userEmail, password);
	}

	public Admin loginAdmin(String adminEmail, String adminPassword) {
		return ir.loginAdmin(adminEmail, adminPassword);
	}

	public List<Policy> getAQuote(String policyFor) {
		return ir.getAQuote(policyFor);
	}

	public void buyMotorInsurance(Vehicle vehicle) {
		ir.buyMotorInsurance(vehicle);
		
	}

	public void buyTravelInsurance(Travel travel) {
		ir.buyTravelInsurance(travel);
		
	}

	public void renewMotorInsurance(CustomerVehiclePolicy cvp) {
		ir.renewMotorInsurance(cvp);
		
	}

	public void renewTravelInsurance(CustomerTravelPolicy ctp) {
		ir.renewTravelInsurance(ctp);
	}

	public void addNewQuery(ContactUs contactUs) {
		ir.addNewQuery(contactUs);
		
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

	public void addMotorClaim(VehicleClaim claim) {
		ir.addMotorClaim(claim);
	}

	public Customer findCustomerById(int custId) {
		return ir.findCustomerById(custId);
	}

	public void addOrUpdatePolicy(Policy policy) {
		ir.addOrUpdatePolicy(policy);
		
	}

	public void addTravelClaim(TravelClaim claim) {
		ir.addTravelClaim(claim);
		
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

	public void updateVehicleClaimStatus(VehicleClaim vc, String status) {
		ir.updateVehicleClaimStatus(vc, status);
		
	}

	public VehicleClaim getVehicleClaimById(int claimId) {
		return ir.getVehicleClaimById(claimId);
	}

	public void updateTravelClaimStatus(TravelClaim tc) {
		ir.updateTravelClaimStatus(tc);
		
	}

	public TravelClaim getTravelClaimById(int claimId) {
		return ir.getTravelClaimById(claimId);
	}

}

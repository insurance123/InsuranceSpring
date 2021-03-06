package com.lti.repository;

import java.util.List;


import com.lti.dto.CustomerTravelPolicyDto;
import com.lti.dto.CustomerVehiclePolicyDto;
import com.lti.dto.LoginStatus;
import com.lti.dto.TravelDto;
import com.lti.dto.VehicleDto;

import com.lti.dto.CustomerVehiclePolicyDto;
import com.lti.entity.Admin;
import com.lti.entity.ContactUs;
import com.lti.entity.Customer;
import com.lti.entity.CustomerTravelPolicy;
import com.lti.entity.CustomerVehiclePolicy;
import com.lti.entity.Document;
import com.lti.entity.Policy;
import com.lti.entity.Travel;
import com.lti.entity.TravelClaim;
import com.lti.entity.Vehicle;
import com.lti.entity.VehicleClaim;



public interface InsuranceRepo {
	public void addOrUpdateCustomer(Customer customer);
	public void registerAdmin(Admin admin);
	public LoginStatus loginCustomer(String userEmail, String password);
	public LoginStatus loginAdmin(String adminEmail, String adminPassword);
	public List<Policy> getAQuote(String policyFor);
	public Vehicle addVehicle(VehicleDto vehicle);
	public CustomerVehiclePolicy buyMotorInsurance(CustomerVehiclePolicyDto cvp);
	public Travel addTravel(TravelDto travelDto);
	public CustomerTravelPolicy buyTravelInsurance(CustomerTravelPolicyDto ctp);
	public Vehicle addVehicle(Vehicle vehicle);
	public Travel addTravel(Travel travel);
	public CustomerTravelPolicy buyTravelInsurance(CustomerTravelPolicy ctp);
	public CustomerVehiclePolicy renewMotorInsurance(CustomerVehiclePolicy oldCvp,CustomerVehiclePolicy newCvp);
	public CustomerTravelPolicy renewTravelInsurance(CustomerTravelPolicy ctp);
	public void addNewQuery(ContactUs contactUs);
	public String fetchQueryWithQueryId(int queryId);
	public List<ContactUs> viewAllQueries();
	public List<CustomerVehiclePolicy> ViewUserMotorPolicies(int customerId);
	public List<CustomerTravelPolicy> viewUserTravelPolicies(int customerId);
	public VehicleClaim addMotorClaim(VehicleClaim claim);
	public Customer findCustomerById(int custId);
	public void addOrUpdatePolicy(Policy policy);
	public TravelClaim addTravelClaim(TravelClaim claim);
	public Policy findPolicyById(int policyId);
	public CustomerVehiclePolicy findCustomerVehiclePolicyById(int id);
	public CustomerTravelPolicy findCustomerTravelPolicyById(int id);
	public List<VehicleClaim> viewMotorClaims(int customerId);
	public List<TravelClaim> viewTravelClaims(int customerId);
	public List<VehicleClaim> viewPendingMotorClaims();
	public List<TravelClaim> viewPendingTravelClaims();
	public VehicleClaim updateVehicleClaimStatus(VehicleClaim vc);
	public VehicleClaim getVehicleClaimById(int claimId);
	public TravelClaim updateTravelClaimStatus(TravelClaim tc);
	public TravelClaim getTravelClaimById(int claimId);
	public Vehicle findVehicleById(int vehicleId);
	public Travel findTravelById(int travelId);
	public List<Policy> getPolicyFor(String policyFor);
	//public boolean isQueryPresent(String userEmail);
	boolean isCustomerPresent(String email);
	public Document addRc(Document document);
	public List<Policy> viewAllPolicies();
	public List<VehicleClaim> viewAllMotorClaims();
	public List<TravelClaim> viewAllTravelClaims();
	public void removeVehiclePolicy(CustomerVehiclePolicy cvp);
	public void removeVehicleClaim(VehicleClaim vc);
	//forgot password
	Customer findCustomerByEmail(String userEmail);
	String updateCustomerPassword(String password,String userEmail);
	public int Generateotp();
}

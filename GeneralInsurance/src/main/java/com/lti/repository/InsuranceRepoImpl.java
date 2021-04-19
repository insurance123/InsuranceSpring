package com.lti.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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


@Repository
public class InsuranceRepoImpl implements InsuranceRepo{
	@PersistenceContext
	EntityManager em;
	

	//addCustomer
	@Transactional
	public void addOrUpdateCustomer(Customer customer) {
		em.merge(customer);
	}
	
	// Register a new Admin
	@Transactional
	public void registerAdmin(Admin admin) {
		em.merge(admin);
	}
		
// Check customer login creds
		public Customer loginCustomer(String userEmail, String password) {
			String jpql = "select c from Customer c where c.userEmail=:uEmail AND c.password=:pass";
			Query query = em.createQuery(jpql);
			query.setParameter("uEmail", userEmail);
			query.setParameter("pass", password);
			Customer customer = (Customer) query.getSingleResult();

			return customer;
		}
		
		// Admin login creds
		public Admin loginAdmin(String adminEmail, String adminPassword) {
			String jpql = "select a from Admin a where a.adminEmail=:aEmail AND a.password=:pass";
			Query query = em.createQuery(jpql);
			query.setParameter("aEmail", adminEmail);
			query.setParameter("pass", adminPassword);
			Admin admin = (Admin) query.getSingleResult();

			return admin;
		}
		
		//getAQuote
		@Transactional
		public List<Policy> getAQuote(String policyFor){
			String jpql = "select p from Policy p where p.policyFor=:pfor";
			TypedQuery<Policy> query = em.createQuery(jpql,Policy.class);
			query.setParameter("pfor",policyFor);
			List<Policy> policy = query.getResultList();
			return policy;
		}
		
			//buyMotorInsurance
			@Transactional
			public CustomerVehiclePolicy buyMotorInsurance(CustomerVehiclePolicy cvp) {
				return em.merge(cvp);
			}
			
			//addVehicle
			@Transactional
			public Vehicle addVehicle(Vehicle vehicle) {
				return em.merge(vehicle);
			}
		
			//buyTravelInsurance
			@Transactional
			public CustomerTravelPolicy buyTravelInsurance(CustomerTravelPolicy ctp) {
				return em.merge(ctp);
			}
			
			//addTravel
			@Transactional
			public Travel addTravel(Travel travel) {
				return em.merge(travel);
			}
	
		//renewMotorInsurance
		@Transactional
		public CustomerVehiclePolicy renewMotorInsurance(CustomerVehiclePolicy cvp){
			return em.merge(cvp);
			
			
		}
		
		//renewTravelInsurance
		@Transactional
		public CustomerTravelPolicy renewTravelInsurance(CustomerTravelPolicy ctp){
			return em.merge(ctp);
		}
			
		@Transactional
		public void addNewQuery(ContactUs contactUs) {
			em.merge(contactUs);

		}
		
		
		// Fetch all queries in contactsUS table
		public List<ContactUs> viewAllQueries() {
			String jpql = "select q from ContactUs q";
			TypedQuery<ContactUs> query = em.createQuery(jpql, ContactUs.class);
			List<ContactUs> queries = query.getResultList();
			return queries;
		}
		
		//ViewUserMotorPolicies
		@Transactional
		public List<CustomerVehiclePolicy> ViewUserMotorPolicies(int customerId){	
//			Customer customer = em.find(Customer.class, customerId);
//			List<CustomerVehiclePolicy> policies = customer.getCustomerVehiclePolicy();
			String jpql = "select cvp from CustomerVehiclePolicy cvp where cvp.customer.userId=:id";
			TypedQuery<CustomerVehiclePolicy> query = em.createQuery(jpql, CustomerVehiclePolicy.class);
			query.setParameter("id", customerId);
			List<CustomerVehiclePolicy> policies = query.getResultList();
			return policies;
		}
		
		//ViewUserTravelPolicies
		@Transactional
		public List<CustomerTravelPolicy> viewUserTravelPolicies(int customerId){
//			Customer customer = em.find(Customer.class, customerId);
//			List<CustomerTravelPolicy> policies = customer.getCustomerTravelPolicy();
			String jpql = "select ctp from CustomerTravelPolicy ctp where ctp.customer.userId=:id";
			TypedQuery<CustomerTravelPolicy> query = em.createQuery(jpql, CustomerTravelPolicy.class);
			query.setParameter("id", customerId);
			List<CustomerTravelPolicy> policies = query.getResultList();
			return policies;
		}
		
		//viewMotorClaims
		@Transactional
		public List<VehicleClaim> viewMotorClaims(int customerId) {
			System.out.println(customerId);
			Customer customer = em.find(Customer.class, customerId);
			List<CustomerVehiclePolicy> policies = customer.getCustomerVehiclePolicy();
			List<VehicleClaim> claims = new ArrayList<VehicleClaim>();
			for(CustomerVehiclePolicy cvp: policies) {
				claims.add(cvp.getVehicleClaim());
			}
			return claims;
		}
		
		//viewTravelPolicy
		@Transactional
		public List<TravelClaim> viewTravelClaims(int customerId) {
			Customer customer = em.find(Customer.class, customerId);
			List<CustomerTravelPolicy> policies = customer.getCustomerTravelPolicy();
			List<TravelClaim> claims = new ArrayList<TravelClaim>();
			for(CustomerTravelPolicy ctp: policies) {
				claims.add(ctp.getTravelClaim());
			}
			return claims;
		}
		
		
		//viewPendingMotorClaims
		public List<VehicleClaim> viewPendingMotorClaims(){        
	        String jpql="select vc from VehicleClaim vc where vc.claimStatus =:status";
	        TypedQuery<VehicleClaim> query=em.createQuery(jpql,VehicleClaim.class);
	        query.setParameter("status", false);
	        return query.getResultList();
	    }
		
		//viewPendingTravelClaims
		public List<TravelClaim> viewPendingTravelClaims(){        
	        String jpql="select tc from TravelClaim tc where tc.claimStatus =:status";
	        TypedQuery<TravelClaim> query=em.createQuery(jpql,TravelClaim.class);
	        query.setParameter("status", false);
	        return query.getResultList();
	    }

		
		//updateVehicleClaimStatus
		@Transactional
		public void updateVehicleClaimStatus(VehicleClaim vc, String status){
			vc.setClaimStatus(ClaimStatus.ACCEPTED);
			em.merge(vc);
		}
		public VehicleClaim getVehicleClaimById(int claimId){
			return em.find(VehicleClaim.class, claimId);
		}
		
		//updateTravelClaimStatus
			@Transactional
			public void updateTravelClaimStatus(TravelClaim tc){
				em.merge(tc);
			}
			public TravelClaim getTravelClaimById(int claimId){
				return em.find(TravelClaim.class, claimId);
			}

		
		//applyMotorClaim
		@Transactional
		public void addMotorClaim(VehicleClaim claim) {
			em.merge(claim);
		}
		
		//addPolicy
		@Transactional
		public void addOrUpdatePolicy(Policy policy) {
			em.merge(policy);
		}

		//applyTravelClaim
		@Transactional
		public void addTravelClaim(TravelClaim claim) {
			em.merge(claim);
		}
			
		
	//findCustomerById
	public Customer findCustomerById(int custId) {
		return em.find(Customer.class, custId);
	}
	
	//findPolicyById
	public Policy findPolicyById(int policyId) {
		return em.find(Policy.class, policyId);
	}
	
	//findVehiclePolicyById
	public CustomerVehiclePolicy findCustomerVehiclePolicyById(int id) {
		return em.find(CustomerVehiclePolicy.class, id);
	}
	
	//findTravelPolicyById
	public CustomerTravelPolicy findCustomerTravelPolicyById(int id) {
		return em.find(CustomerTravelPolicy.class, id);
	}
	
	// Fetch query with queryID
	public String fetchQueryWithQueryId(int queryId) {
		ContactUs contactUs = em.find(ContactUs.class, queryId);
		return contactUs.getQuery();
	}
	
	//findVehicleById
	public Vehicle findVehicleById(int vehicleId) {
		return em.find(Vehicle.class, vehicleId);
	}
	
	//findTravelById
	public Travel findTravelById(int travelId) {
		return em.find(Travel.class, travelId);
	}

	
	
	
		
		
		
		
		
		
		
		
		
		
		
	
}

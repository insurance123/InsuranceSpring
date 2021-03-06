package com.lti.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
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
import com.lti.entity.Document;
import com.lti.entity.Policy;
import com.lti.entity.Travel;
import com.lti.entity.TravelClaim;
import com.lti.entity.Vehicle;
import com.lti.entity.VehicleClaim;


@Repository
public class InsuranceRepoImpl implements InsuranceRepo{
	@PersistenceContext
	EntityManager em;
	
	@PersistenceContext
	EntityManager em1;

	@PersistenceContext
	EntityManager em2;
	//addCustomer
	@Transactional
	public void addOrUpdateCustomer(Customer customer) {
		em.merge(customer);
	}
	
	@Override
    public boolean isCustomerPresent(String email) {
         return (Long)
                    em
                    .createQuery("select count(c.userId) from Customer c where c.userEmail = :em")
                    .setParameter("em", email)
                    .getSingleResult() == 1 ? true : false;
    }
	
	// Register a new Admin
	@Transactional
	public void registerAdmin(Admin admin) {
		em.merge(admin);
	}
		
// Check customer login creds
		public LoginStatus loginCustomer(String userEmail, String password) {
			String jpql = "select c from Customer c where c.userEmail=:uEmail AND c.password=:pass";
			Query query = em.createQuery(jpql);
			query.setParameter("uEmail", userEmail);
			query.setParameter("pass", password);
			LoginStatus lstatus = new LoginStatus();
			try {
					Customer customer = (Customer) query.getSingleResult();
					lstatus.setUserId(customer.getUserId());
					lstatus.setUserEmail(customer.getUserEmail());
					lstatus.setUserName(customer.getUserName());
					lstatus.setUserStatus("Success");
					return lstatus;
			}
			
			catch (Exception e) {
				lstatus.setUserStatus("Failed");
				return lstatus;
			}
		}
		
		// Admin login creds
		public LoginStatus loginAdmin(String adminEmail, String adminPassword) {
			String jpql = "select a from Admin a where a.adminEmail=:aEmail AND a.password=:pass";
			Query query = em.createQuery(jpql);
			query.setParameter("aEmail", adminEmail);
			query.setParameter("pass", adminPassword);
			LoginStatus lstatus = new LoginStatus();
			try {
				Admin admin = (Admin) query.getSingleResult();
				lstatus.setAdminId(admin.getAdminId());
				lstatus.setAdminEmail(admin.getAdminEmail());
				lstatus.setAdminStatus("Success");
				return lstatus;
			}
			catch (Exception e) {
				lstatus.setAdminStatus("Failed");
				return lstatus;
			}


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
			public CustomerVehiclePolicy buyMotorInsurance(CustomerVehiclePolicyDto cvp) {
				CustomerVehiclePolicy cp = new CustomerVehiclePolicy();
				cp.setStartDate(cvp.getStartDate());
				cp.setEndDate(cvp.getEndDate());
				cp.setCoverageAmount(cvp.getCoverageAmount());
				cp.setPremiumAmount(cvp.getPremiumAmount());
				cp.setCustomer(findCustomerById(cvp.getCustomerId()));
				cp.setPolicy(findPolicyById(cvp.getPolicyId()));
				cp.setVehicle(findVehicleById(cvp.getVehicleId()));
				return em.merge(cp);
			}
			
			//addVehicle
			//addVehicle
            @Transactional
            public Vehicle addVehicle(VehicleDto vehicleDto) {
                Vehicle vehicle = new Vehicle();
                vehicle.setRegistrationNumber(vehicleDto.getRegistrationNumber());
                vehicle.setRegistrationState(vehicleDto.getRegistrationState());
                vehicle.setManufacturer(vehicleDto.getManufacturer());
                vehicle.setModel(vehicleDto.getModel());
                vehicle.setFuelType(vehicleDto.getFuelType());
                vehicle.setVehicleType(vehicleDto.getVehicleType());
                vehicle.setEngineNumber(vehicleDto.getEngineNumber());
                vehicle.setChassisNumber(vehicleDto.getChassisNumber());
                vehicle.setCustomer(findCustomerById(vehicleDto.getUserId()));
                vehicle.setAge(vehicle.getAge());
                return em.merge(vehicle);
            }
		
			//buyTravelInsurance
			@Transactional
			public CustomerTravelPolicy buyTravelInsurance(CustomerTravelPolicyDto ctp) {
				CustomerTravelPolicy cp = new CustomerTravelPolicy();
				cp.setStartDate(ctp.getStartDate());
				cp.setEndDate(ctp.getEndDate());
				cp.setCoverageAmount(ctp.getCoverageAmount());
				cp.setPremiumAmount(ctp.getPremiumAmount());
				cp.setCustomer(findCustomerById(ctp.getCustomerId()));
				cp.setPolicy(findPolicyById(ctp.getPolicyId()));
				cp.setTravel(findTravelById(ctp.getTravelId()));
				return em.merge(cp);
			}
			
			//addTravel
			@Transactional
			public Travel addTravel(TravelDto travelDto) {
				Travel travel = new Travel();
				travel.setStartDate(travelDto.getStartDate());
				travel.setEndDate(travelDto.getEndDate());
				travel.setSource(travelDto.getSource());
				travel.setDestination(travelDto.getDestination());
				travel.setModeOfTransport(travelDto.getModeOfTransport());
				travel.setCustomer(findCustomerById(travelDto.getUserId()));
				return em.merge(travel);
			}
	
		//renewMotorInsurance
		@Transactional
		public CustomerVehiclePolicy renewMotorInsurance(CustomerVehiclePolicy oldCvp, CustomerVehiclePolicy newCvp){
			System.out.println(oldCvp.getCustomerVehiclePolicyId());
			try {
				VehicleClaim vc  = oldCvp.getVehicleClaim();
				removeVehicleClaim(vc);
				//em1.detach(vc);
			}
			catch (Exception e) {
				
			}
//			policies.setCustomerVehiclePolicyId(oldCvp.getCustomerVehiclePolicyId());
//			policies.setEndDate(oldCvp.getEndDate());
//			policies.setPremiumAmount(oldCvp.getPremiumAmount());
//			policies.setCoverageAmount(oldCvp.getCoverageAmount());
//			policies.setStartDate(oldCvp.getStartDate());
//			policies.setCustomer(oldCvp.getCustomer());
//			policies.setVehicle(oldCvp.getVehicle());
//			policies.setPolicy(oldCvp.getPolicy());
//			em.merge(policies);
			//em2.detach(oldCvp);
			removeVehiclePolicy(oldCvp);
			return em.merge(newCvp);
			
			
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
		
		/*
		 * @Override public boolean isQueryPresent(String userEmail) { return (Long) em
		 * .createQuery("select count(cu.queryId) from ContactUs cu where cu.userEmail =  :em"
		 * ) .setParameter("em", userEmail) .getSingleResult() == 1 ? true : false;
		 * 
		 * }
		 */
		
		
		
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
			//System.out.println(customerId);
			String jpql = "select vc from VehicleClaim vc where vc.customerVehiclePolicy.customer.userId=:custId";
			TypedQuery<VehicleClaim> query = em.createQuery(jpql, VehicleClaim.class);
			query.setParameter("custId", customerId);
			List<VehicleClaim> claims = query.getResultList();
			/*
			 * Customer customer = em.find(Customer.class, customerId);
			 * List<CustomerVehiclePolicy> policies = customer.getCustomerVehiclePolicy();
			 * List<VehicleClaim> claims = new ArrayList<VehicleClaim>();
			 * for(CustomerVehiclePolicy cvp: policies) { claims.add(cvp.getVehicleClaim());
			 * }
			 */
			return claims;
		}
		
		//viewTravelPolicy
		@Transactional
		public List<TravelClaim> viewTravelClaims(int customerId) {
			String jpql = "select tc from TravelClaim tc where tc.customertravelpolicy.customer.userId=:custId";
			TypedQuery<TravelClaim> query = em.createQuery(jpql, TravelClaim.class);
			query.setParameter("custId", customerId);
			List<TravelClaim> claims = query.getResultList();
			/*
			 * Customer customer = em.find(Customer.class, customerId);
			 * List<CustomerTravelPolicy> policies = customer.getCustomerTravelPolicy();
			 * List<TravelClaim> claims = new ArrayList<TravelClaim>();
			 * for(CustomerTravelPolicy ctp: policies) { claims.add(ctp.getTravelClaim()); }
			 */
			return claims;
		}
		
		
		//viewPendingMotorClaims
		public List<VehicleClaim> viewPendingMotorClaims(){        
	        String jpql="select vc from VehicleClaim vc where vc.claimStatus =:status";
	        TypedQuery<VehicleClaim> query=em.createQuery(jpql,VehicleClaim.class);
	        query.setParameter("status", ClaimStatus.PENDING);
	        return query.getResultList();
	    }
		
		//viewPendingTravelClaims
		public List<TravelClaim> viewPendingTravelClaims(){        
	        String jpql="select tc from TravelClaim tc where tc.claimStatus =:status";
	        TypedQuery<TravelClaim> query=em.createQuery(jpql,TravelClaim.class);
	        query.setParameter("status", ClaimStatus.PENDING);
	        return query.getResultList();
	    }

		
		//updateVehicleClaimStatus
		@Transactional
		public VehicleClaim updateVehicleClaimStatus(VehicleClaim vc){
			//vc.setClaimStatus(ClaimStatus.ACCEPTED);
			 return em.merge(vc);
		}
		
		//updateTravelClaimStatus
		@Transactional
		public TravelClaim updateTravelClaimStatus(TravelClaim tc){
			return em.merge(tc);
		}
		
		
		public VehicleClaim getVehicleClaimById(int claimId){
			return em.find(VehicleClaim.class, claimId);
		}
		
		
			public TravelClaim getTravelClaimById(int claimId){
				return em.find(TravelClaim.class, claimId);
			}

		
		//applyMotorClaim
		@Transactional
		public VehicleClaim addMotorClaim(VehicleClaim claim) {
			claim.setClaimStatus(ClaimStatus.PENDING);
			em.merge(claim);
			return claim;
		}
		
		//addPolicy
		@Transactional
		public void addOrUpdatePolicy(Policy policy) {
			em.merge(policy);
		}

		//applyTravelClaim
		@Transactional
		public TravelClaim addTravelClaim(TravelClaim claim) {
			claim.setClaimStatus(ClaimStatus.PENDING);
			em.merge(claim);
			return claim;
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

	@Override
	public List<Policy> getPolicyFor(String policyFor) {
		// TODO Auto-generated method stub
		String jpql = "select p from Policy p where p.policyFor=:pfor";
		TypedQuery<Policy> query = em.createQuery(jpql,Policy.class);
		query.setParameter("pfor",policyFor);
		List<Policy> policy = query.getResultList();
		return policy;
	}

	@Override
	public Vehicle addVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Travel addTravel(Travel travel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerTravelPolicy buyTravelInsurance(CustomerTravelPolicy ctp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public Document addRc(Document document) {
		// TODO Auto-generated method stub
		return em.merge(document);
	}

	@Override
	public List<Policy> viewAllPolicies() {
		String jpql = "select p from Policy p";
		TypedQuery<Policy> query = em.createQuery(jpql,Policy.class);
		List<Policy> policy = query.getResultList();
		return policy;
	}

	//forgot password:
	@Override
    public Customer findCustomerByEmail(String userEmail) {
        String jpql = "select c from Customer c where c.userEmail=:uEmail";
        Query query = em.createQuery(jpql);
        query.setParameter("uEmail", userEmail);
        Customer cust = (Customer) query.getSingleResult();
        return cust;
    }
	
	
	 @Transactional
	    public String updateCustomerPassword(String password, String userEmail) {
	        String jpql = "update Customer c set c.password=:up where c.userEmail=:uid";
	        Query query = em.createQuery(jpql);
	        query.setParameter("up", password);
	        query.setParameter("uid", userEmail);
	        int updatedRows = query.executeUpdate();
	        if (updatedRows > 0)
	            return "updated";
	        
	        return "failed";
	    }
		
	 @Override
	    public int Generateotp() {
	        Random rand = new Random(); // instance of random class
	        int upperbound = 100000;
	        // generate random values from 0-24
	        int int_random = rand.nextInt(upperbound);
	        return int_random;
	    }

	@Override
	public List<VehicleClaim> viewAllMotorClaims() {
		String jpql = "select v from VehicleClaim v";
		TypedQuery<VehicleClaim> query = em.createQuery(jpql,VehicleClaim.class);
		List<VehicleClaim> vc = query.getResultList();
		return vc;
	}

	@Override
	public List<TravelClaim> viewAllTravelClaims() {
		String jpql = "select t from TravelClaim t";
		TypedQuery<TravelClaim> query = em.createQuery(jpql,TravelClaim.class);
		List<TravelClaim> tc = query.getResultList();
		return tc;
	}

	@Override
	@Transactional
	public void removeVehiclePolicy(CustomerVehiclePolicy cvp) {
		// TODO Auto-generated method stub
		int id = cvp.getCustomerVehiclePolicyId();
		String jpql = "delete from CustomerVehiclePolicy c where c.customerVehiclePolicyId=:cvpId";
		Query query = em.createQuery(jpql);
		query.setParameter("cvpId", id);
		int rec = query.executeUpdate();
		System.out.println(rec);
		//em.detach(cvp);
		
	}

	@Override
	@Transactional
	public void removeVehicleClaim(VehicleClaim vc) {
		// TODO Auto-generated method stub
		int id = vc.getClaimId();
		String jpql = "delete from VehicleClaim v where v.claimId=:vId";
		Query query = em.createQuery(jpql);
		query.setParameter("vId", id);
		int rec = query.executeUpdate();
		System.out.println(rec);
		//em.detach(vc);
	}
		
		
		
		
		
		
		
		
	
}

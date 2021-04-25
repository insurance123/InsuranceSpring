package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="customertravelpolicy")
public class CustomerTravelPolicy {
	@Id
	@SequenceGenerator(name="custtravelpolicy_seq", initialValue=7001, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="custtravelpolicy_seq")
	int customerTravelPolicyId;
	LocalDate startDate;
	LocalDate endDate;
	double coverageAmount;
	double premiumAmount;
	
	@OneToOne(mappedBy="customertravelpolicy", cascade=CascadeType.ALL)
	TravelClaim travelClaim;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	Customer customer;
	
	@ManyToOne
	@JoinColumn(name="policyId")
	Policy policy;
	
	@ManyToOne
	@JoinColumn(name="travelId")
	Travel travel;
	
	public int getCustomerTravelPolicyId() {
		return customerTravelPolicyId;
	}
	public void setCustomerTravelPolicyId(int customerTravelPolicyId) {
		this.customerTravelPolicyId = customerTravelPolicyId;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public double getCoverageAmount() {
		return coverageAmount;
	}
	public void setCoverageAmount(double coverageAmount) {
		this.coverageAmount = coverageAmount;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	public Travel getTravel() {
		return travel;
	}
	public void setTravel(Travel travel) {
		this.travel = travel;
	}
	@JsonIgnore
	public TravelClaim getTravelClaim() {
		return travelClaim;
	}
	public void setTravelClaim(TravelClaim travelClaim) {
		this.travelClaim = travelClaim;
	}
	public double getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	
	
	
}

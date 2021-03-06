package com.lti.dto;

import java.time.LocalDate;

public class CustomerTravelPolicyDto {
	
	int customerTravelPolicyId;
	LocalDate startDate;
	LocalDate endDate;
	double coverageAmount;
	double premiumAmount;
	int customerId;
	int travelId;
	int policyId;
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
	
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getTravelId() {
		return travelId;
	}
	public void setTravelId(int travelId) {
		this.travelId = travelId;
	}
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public double getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	
	

}

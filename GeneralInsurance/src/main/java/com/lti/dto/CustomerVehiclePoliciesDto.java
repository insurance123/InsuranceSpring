package com.lti.dto;

import java.time.LocalDate;

public class CustomerVehiclePoliciesDto {
	
	int customerVehiclePolicyId;
	LocalDate startDate;
	LocalDate endDate;
	double coverageAmount;
	double premiumAmount;
	int customerId;
	int policyId;
	int vehicleId;
	String claimStatus;
	
	public int getCustomerVehiclePolicyId() {
		return customerVehiclePolicyId;
	}
	public void setCustomerVehiclePolicyId(int customerVehiclePolicyId) {
		this.customerVehiclePolicyId = customerVehiclePolicyId;
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
	public double getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}
	

}

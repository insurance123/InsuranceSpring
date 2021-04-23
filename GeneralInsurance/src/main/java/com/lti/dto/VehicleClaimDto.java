package com.lti.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lti.entity.ClaimStatus;

public class VehicleClaimDto {
	int claimId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate claimDate;
	String reasonOfClaim;
	MultipartFile proofOfClaim;
	double claimAmount;
	int customerVehiclePolicyId;
	public int getClaimId() {
		return claimId;
	}
	public void setClaimId(int claimId) {
		this.claimId = claimId;
	}
	public LocalDate getClaimDate() {
		return claimDate;
	}
	public void setClaimDate(LocalDate claimDate) {
		this.claimDate = claimDate;
	}
	public String getReasonOfClaim() {
		return reasonOfClaim;
	}
	public void setReasonOfClaim(String reasonOfClaim) {
		this.reasonOfClaim = reasonOfClaim;
	}
	public MultipartFile getProofOfClaim() {
		return proofOfClaim;
	}
	public void setProofOfClaim(MultipartFile proofOfClaim) {
		this.proofOfClaim = proofOfClaim;
	}
	
	public double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}
	public int getCustomerVehiclePolicyId() {
		return customerVehiclePolicyId;
	}
	public void setCustomerVehiclePolicyId(int customerVehiclePolicyId) {
		this.customerVehiclePolicyId = customerVehiclePolicyId;
	}
	
	
}

package com.lti.dto;

import java.time.LocalDate;

import com.lti.entity.ClaimStatus;

public class ViewVehicleClaimDto {
	int claimId;
	LocalDate claimDate;
	String reasonOfClaim;
	String proofOfClaim;
	ClaimStatus claimStatus;
	double claimAmount;
	int policyId;
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
	public String getProofOfClaim() {
		return proofOfClaim;
	}
	public void setProofOfClaim(String proofOfClaim) {
		this.proofOfClaim = proofOfClaim;
	}
	public ClaimStatus getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(ClaimStatus claimStatus) {
		this.claimStatus = claimStatus;
	}
	public double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	
	

}
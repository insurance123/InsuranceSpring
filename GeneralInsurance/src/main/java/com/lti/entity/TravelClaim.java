package com.lti.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="travel_claim")
public class TravelClaim {
	@Id
	@SequenceGenerator(name="travelclaim_seq", initialValue=7001, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="travelclaim_seq")
	int claimId;
	LocalDate claimDate;
	ClaimStatus claimStatus;
	String reasonOfClaim;
	String proofOfClaim;
	double claimAmount;
	
	@OneToOne
	@JoinColumn(name="customerTravelPolicyId")
	CustomerTravelPolicy customertravelpolicy;
	
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
	
	public ClaimStatus getClaimStatus() {
		return claimStatus;
	}
	public void setClaimStatus(ClaimStatus claimStatus) {
		this.claimStatus = claimStatus;
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
	
	
	public double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}
	@JsonIgnore
	public CustomerTravelPolicy getCustomerTravelPolicy() {
		return customertravelpolicy;
	}
	public void setCustomerTravelPolicy(CustomerTravelPolicy customerTravelPolicy) {
		this.customertravelpolicy = customerTravelPolicy;
	}
	
	
}

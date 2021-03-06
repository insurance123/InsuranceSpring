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

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="vehicle_claim")
public class VehicleClaim {
	@Id
	@SequenceGenerator(name="vehicleclaim_seq", initialValue=6001, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="vehicleclaim_seq")
	int claimId;
	LocalDate claimDate;
	String reasonOfClaim;
	String proofOfClaim;
	ClaimStatus claimStatus;
	double claimAmount;
	@OneToOne
	@JoinColumn(name="customerVehiclePolicyId")
	CustomerVehiclePolicy customerVehiclePolicy;
	
	
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
	
	public double getClaimAmount() {
		return claimAmount;
	}
	public void setClaimAmount(double claimAmount) {
		this.claimAmount = claimAmount;
	}
	@JsonIgnore
	public CustomerVehiclePolicy getCustomerVehiclePolicy() {
		return customerVehiclePolicy;
	}
	public void setCustomerVehiclePolicy(CustomerVehiclePolicy customerVehiclePolicy) {
		this.customerVehiclePolicy = customerVehiclePolicy;
	}
	public String getProofOfClaim() {
		return proofOfClaim;
	}
	public void setProofOfClaim(String proofOfClaim) {
		this.proofOfClaim = proofOfClaim;
	}
	
	
	
}

package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="policy_table")
public class Policy {
	@Id
	@SequenceGenerator(name="policy_seq", initialValue=2001, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="policy_seq")
	int policyId;
	String policyName;
	double duration;
	double premiumAmount;
	String policyFor;
	
	@OneToMany(mappedBy="policy", cascade=CascadeType.ALL)
	List<CustomerVehiclePolicy> customerVehiclePolicy;
	
	@OneToMany(mappedBy="policy", cascade=CascadeType.ALL)
	List<CustomerTravelPolicy> customerTravelPolicy;
	
	
	public int getPolicyId() {
		return policyId;
	}
	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}
	public String getPolicyName() {
		return policyName;
	}
	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}
	public double getDuration() {
		return duration;
	}
	public void setDuration(double duration) {
		this.duration = duration;
	}
	public double getPremiumAmount() {
		return premiumAmount;
	}
	public void setPremiumAmount(double premiumAmount) {
		this.premiumAmount = premiumAmount;
	}
	public String getPolicyFor() {
		return policyFor;
	}
	public void setPolicyFor(String policyFor) {
		this.policyFor = policyFor;
	}
	
	@JsonIgnore
	public List<CustomerVehiclePolicy> getCustomerVehiclePolicy() {
		return customerVehiclePolicy;
	}
	public void setCustomerVehiclePolicy(List<CustomerVehiclePolicy> customerVehiclePolicy) {
		this.customerVehiclePolicy = customerVehiclePolicy;
	}
	@JsonIgnore
	public List<CustomerTravelPolicy> getCustomerTravelPolicy() {
		return customerTravelPolicy;
	}
	public void setCustomerTravelPolicy(List<CustomerTravelPolicy> customerTravelPolicy) {
		this.customerTravelPolicy = customerTravelPolicy;
	}
	
	
	
	
}

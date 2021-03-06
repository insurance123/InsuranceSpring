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
@Table(name="customervehiclepolicy")
public class CustomerVehiclePolicy {
	@Id
	@SequenceGenerator(name="custvehiclepolicy_seq", initialValue=7001, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="custvehiclepolicy_seq")
	int customerVehiclePolicyId;
	LocalDate startDate;
	LocalDate endDate;
	double coverageAmount;
	double premiumAmount;
	
	
	@ManyToOne
	@JoinColumn(name="customerId")
	Customer customer;
	
	@ManyToOne
	@JoinColumn(name="policyId")
	Policy policy;
	
	@ManyToOne
	@JoinColumn(name="vehicleId")
	Vehicle vehicle;
	
	@OneToOne(mappedBy="customerVehiclePolicy", cascade=CascadeType.ALL)
	VehicleClaim vehicleClaim;
	
	
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
	@JsonIgnore
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@JsonIgnore
	public Policy getPolicy() {
		return policy;
	}
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	@JsonIgnore
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	public VehicleClaim getVehicleClaim() {
		return vehicleClaim;
	}
	public void setVehicleClaim(VehicleClaim vehicleClaim) {
		this.vehicleClaim = vehicleClaim;
	}
	
	
}

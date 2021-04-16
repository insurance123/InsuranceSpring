package com.lti.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="vehicle_table")
public class Vehicle {
	@Id
	@SequenceGenerator(name="vehicle_seq", initialValue=4001, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="vehicle_seq")
	int vehicleId;
	@Column(unique=true)
	String registrationNumber; 
	String registrationState;
	String manufacturer;
	String model;
	String vehicleType;
	String engineNumber;
	String chassisNumber;
	String fuelType;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	Customer customer;
	
	@OneToMany(mappedBy="vehicle", cascade=CascadeType.ALL)
	List<CustomerVehiclePolicy> customerVehiclePolicy;
	
	
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getRegistrationState() {
		return registrationState;
	}
	public void setRegistrationState(String registrationState) {
		this.registrationState = registrationState;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getVehicleType() {
		return vehicleType;
	}
	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}
	public String getEngineNumber() {
		return engineNumber;
	}
	public void setEngineNumber(String engineNumber) {
		this.engineNumber = engineNumber;
	}
	public String getChassisNumber() {
		return chassisNumber;
	}
	public void setChassisNumber(String chassisNumber) {
		this.chassisNumber = chassisNumber;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@JsonIgnore
	public List<CustomerVehiclePolicy> getCustomerVehiclePolicy() {
		return customerVehiclePolicy;
	}
	public void setCustomerVehiclePolicy(List<CustomerVehiclePolicy> customerVehiclePolicy) {
		this.customerVehiclePolicy = customerVehiclePolicy;
	}
	
	
	
}

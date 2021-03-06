package com.lti.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="customer_table")
@NamedQuery(name = "fetch-all", query = "select c from Customer as c")
public class Customer {
	@Id
	@SequenceGenerator(name="customer_seq", initialValue=1001, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="customer_seq")
	int userId;
	String userName;
	String password;
	String userEmail;
	String userPhone;
	@JsonFormat(pattern="yyyy-MM-dd")
	LocalDate dateOfBirth;
	String aadharCard;
	
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
	List<Vehicle> vehicle;
	
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
	List<Travel> travel;
	
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
	List<CustomerVehiclePolicy> customerVehiclePolicy;
	
	@OneToMany(mappedBy="customer", cascade=CascadeType.ALL)
	List<CustomerTravelPolicy> customerTravelPolicy;
	
	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	List<Document> document;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@JsonIgnore
	public List<Vehicle> getVehicle() {
		return vehicle;
	}
	public void setVehicle(List<Vehicle> vehicle) {
		this.vehicle = vehicle;
	}
	@JsonIgnore
	public List<Travel> getTravel() {
		return travel;
	}
	public void setTravel(List<Travel> travel) {
		this.travel = travel;
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
	public String getAadharCard() {
		return aadharCard;
	}
	public void setAadharCard(String aadharCard) {
		this.aadharCard = aadharCard;
	}
	@JsonIgnore
	public List<Document> getDocument() {
		return document;
	}
	public void setDocument(List<Document> document) {
		this.document = document;
	}
	
	
	
}

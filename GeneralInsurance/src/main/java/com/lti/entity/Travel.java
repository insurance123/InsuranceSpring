package com.lti.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name="travel_table")
public class Travel {
	@Id
	@SequenceGenerator(name="travel_seq", initialValue=5001, allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="travel_seq")
	int travelId;
	LocalDate startDate;
	LocalDate endDate;
	String source;
	String destination;
	String modeOfTransport;
	
	@ManyToOne
	@JoinColumn(name="customerId")
	Customer customer;
	
	@OneToMany(mappedBy="travel", cascade=CascadeType.ALL)
	List<CustomerTravelPolicy> customerTravelPolicy;
	
	public int getTravelId() {
		return travelId;
	}
	public void setTravelId(int travelId) {
		this.travelId = travelId;
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
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public String getModeOfTransport() {
		return modeOfTransport;
	}
	public void setModeOfTransport(String modeOfTransport) {
		this.modeOfTransport = modeOfTransport;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	@JsonIgnore
	public List<CustomerTravelPolicy> getCustomerTravelPolicy() {
		return customerTravelPolicy;
	}
	public void setCustomerTravelPolicy(List<CustomerTravelPolicy> customerTravelPolicy) {
		this.customerTravelPolicy = customerTravelPolicy;
	}
	
	
	
}

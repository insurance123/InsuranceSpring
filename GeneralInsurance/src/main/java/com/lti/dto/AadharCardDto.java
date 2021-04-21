package com.lti.dto;

import org.springframework.web.multipart.MultipartFile;

public class AadharCardDto {
	
	private int userId;
	private MultipartFile aadharCard;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public MultipartFile getAadharCard() {
		return aadharCard;
	}
	public void setAadharCard(MultipartFile aadharCard) {
		this.aadharCard = aadharCard;
	}

}

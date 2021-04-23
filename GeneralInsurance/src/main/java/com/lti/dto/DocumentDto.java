package com.lti.dto;

import org.springframework.web.multipart.MultipartFile;

public class DocumentDto {
	private int userId;
	private MultipartFile rcBook;
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public MultipartFile getRcBook() {
		return rcBook;
	}
	public void setRcBook(MultipartFile rcBook) {
		this.rcBook = rcBook;
	}
	
	

}

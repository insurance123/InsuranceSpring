package com.lti.dto;

import java.time.LocalDate;

public class UserDetailsDto {
	int userId;
    String userName;
    String userEmail;
    String userPhone;
    LocalDate dateOfBirth;
    String aadharCard;

    public String getAadharCard() {
        return aadharCard;
    }
    public void setAadharCard(String aadharCard) {
        this.aadharCard = aadharCard;
    }
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


}

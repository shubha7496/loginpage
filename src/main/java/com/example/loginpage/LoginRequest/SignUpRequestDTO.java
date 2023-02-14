package com.example.loginpage.LoginRequest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class SignUpRequestDTO {
	
	@NotEmpty(message="name is mandetory")
    private String name;
	@NotEmpty(message="name is mandetory")
    private String gender;
	@Email
    private String emailId;
	@NotNull
    private String phoneNumber;
	@NotNull
    private String password;

    public String getName() {
        return name;
    }

    public void setName( String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

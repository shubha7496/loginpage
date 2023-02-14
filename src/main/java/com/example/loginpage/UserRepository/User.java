package com.example.loginpage.UserRepository;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.example.loginpage.Constant;
import com.example.loginpage.Constant.USER_TYPE;

@Entity
public class User {

    @Id
    @GeneratedValue
    @NotNull(message = "not run ")
    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String gender;
    @Email
    private String emailId;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String userType = Constant.USER_TYPE.NORMAL;
    @NotNull
    private String password;
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @NotNull
    private DateTime updatedAt;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(DateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @PrePersist
    public void onSave(){
        // create at and update at
        DateTime currentDateTime = new DateTime();

        this.updatedAt = currentDateTime;
    }

    @PostPersist
    public void onUpdate(){
        // update at
        DateTime currentDateTime = new DateTime();

        this.updatedAt = currentDateTime;
    }


}

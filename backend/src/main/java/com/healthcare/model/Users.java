package com.healthcare.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long userId;

	private String userName;

	private String password;

	private String fullName;

	private Date dob;

	private String mobileNo;

	private String address;

	private String userRole;

	private String emailId;
	
	private String primaryContactPersonName;

	private String primaryContactPersonMobile;

	private String country;

	private Date createAt;

	public Users(long userId, String userName, String password, String fullName, Date dob, String mobileNo,
			String address, String role, String primaryContactPersonName, String primaryContactPersonMobile,
			String country, Date createAt , String emailId) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.dob = dob;
		this.mobileNo = mobileNo;
		this.address = address;
		this.userRole = role;
		this.primaryContactPersonName = primaryContactPersonName;
		this.primaryContactPersonMobile = primaryContactPersonMobile;
		this.country = country;
		this.createAt = createAt;
		this.emailId = emailId;
	}

	public Users() {
		super();
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", password=" + password + ", fullName="
				+ fullName + ", dob=" + dob + ", mobileNo=" + mobileNo + ", address=" + address
				+ ", primaryContactPersonName=" + primaryContactPersonName + ", primaryContactPersonMobile="
				+ primaryContactPersonMobile + ", country=" + country + ", createAt=" + createAt + "]";
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPrimaryContactPersonName() {
		return primaryContactPersonName;
	}

	public void setPrimaryContactPersonName(String primaryContactPersonName) {
		this.primaryContactPersonName = primaryContactPersonName;
	}

	public String getPrimaryContactPersonMobile() {
		return primaryContactPersonMobile;
	}

	public void setPrimaryContactPersonMobile(String primaryContactPersonMobile) {
		this.primaryContactPersonMobile = primaryContactPersonMobile;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getRole() {
		return userRole;
	}

	public void setRole(String role) {
		this.userRole = role;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

}

//CREATE TABLE public.users (
//		user_id bigserial NOT NULL,
//		user_name varchar(255) unique not NULL,
//		full_name varchar(255) not NULL,
//		dob DATE NULL,
//		mobile_no varchar(12) NULL,
//		address varchar(255) NULL,
//		"password" varchar(255) not NULL,
//		country varchar(50) NULL,
//		user_role varchar(15) default 'user',
//		primary_contact_person_name varchar(255) NULL,
//		primary_contact_person_mobile varchar(12) NULL,
//		create_at timestamp default current_timestamp,
//		CONSTRAINT users_pkey PRIMARY KEY (user_id)
//	);

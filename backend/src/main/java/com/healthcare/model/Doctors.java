package com.healthcare.model;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



@Entity
public class Doctors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long doctorId;

	private String doctorName;

	private String mobileNo;

	private String address;

	private long addedBy;
	
	private boolean isActive;
	
	private Date createdAt;
	
	public Doctors() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Doctors(long doctorId, String doctorName, String mobileNo, String address, long addedBy, boolean isActive ,Date createdAt) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.mobileNo = mobileNo;
		this.address = address;
		this.addedBy = addedBy;
		this.createdAt = createdAt;
		this.isActive = isActive;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
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


	public long getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(long addedBy) {
		this.addedBy = addedBy;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
}
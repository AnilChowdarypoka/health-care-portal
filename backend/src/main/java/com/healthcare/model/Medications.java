package com.healthcare.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medications {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long medicationId;
	
	private long pharmacyId;
	
	private long userId;
	
	private Date estimatedDeliveryDate;
	
	private Date bookedDate;

	public Medications(long medicationId, long pharmacyId, long userId, Date estimatedDeliveryDate, Date bookedDate) {
		super();
		this.medicationId = medicationId;
		this.pharmacyId = pharmacyId;
		this.userId = userId;
		this.estimatedDeliveryDate  = estimatedDeliveryDate;
		this.bookedDate = bookedDate;
	}

	public Medications() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getMedicationId() {
		return medicationId;
	}

	public void setMedicationId(long medicationId) {
		this.medicationId = medicationId;
	}

	public long getPharmacyId() {
		return pharmacyId;
	}

	public void setPharmacyId(long pharmacyId) {
		this.pharmacyId = pharmacyId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

	public Date getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}
	
	
	
}

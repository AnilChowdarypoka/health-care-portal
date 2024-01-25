package com.healthcare.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class HealthHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long userId;

	private float systolicBp;

	private float diastolicBp;

	private float bloodOxygenLevel;

	private float bodyTemperature;

	private String previousMedicalHistory;

	private String allergies;

	private String otherSymptoms;

	private Date recordedAt;

	public HealthHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HealthHistory(long id, long userId, float systolicBp, float diastolicBp, float bloodOxygenLevel,
			float bodyTemperature, String previousMedicalHistory, String allergies, String otherSymptoms, Date myDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.systolicBp = systolicBp;
		this.diastolicBp = diastolicBp;
		this.bloodOxygenLevel = bloodOxygenLevel;
		this.bodyTemperature = bodyTemperature;
		this.previousMedicalHistory = previousMedicalHistory;
		this.allergies = allergies;
		this.otherSymptoms = otherSymptoms;
		this.recordedAt = myDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public float getSystolicBp() {
		return systolicBp;
	}

	public void setSystolicBp(float systolicBp) {
		this.systolicBp = systolicBp;
	}

	public float getDiastolicBp() {
		return diastolicBp;
	}

	public void setDiastolicBp(float diastolicBp) {
		this.diastolicBp = diastolicBp;
	}

	public float getBloodOxygenLevel() {
		return bloodOxygenLevel;
	}

	public void setBloodOxygenLevel(float bloodOxygenLevel) {
		this.bloodOxygenLevel = bloodOxygenLevel;
	}

	public float getBodyTemperature() {
		return bodyTemperature;
	}

	public void setBodyTemperature(float bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}

	public String getPreviousMedicalHistory() {
		return previousMedicalHistory;
	}

	public void setPreviousMedicalHistory(String previousMedicalHistory) {
		this.previousMedicalHistory = previousMedicalHistory;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public String getOtherSymptoms() {
		return otherSymptoms;
	}

	public void setOtherSymptoms(String otherSymptoms) {
		this.otherSymptoms = otherSymptoms;
	}

	public void setRecordedAt(Date recordedAt) {
		this.recordedAt = recordedAt;
	}

	public Date getRecordedAt() {
		return recordedAt;
	}

}

//create table health_history(
//id bigserial NOT null primary key,
//user_id int not null,
//systolic_bp float,
//diastolic_bp float,
//blood_oxygen_level float,
//body_temperature float ,
//previous_medical_history text,
//allergies text,
//other_symptoms text,
//recorded_at timestamp,
//constraint health_history_fk foreign key (user_id) references users(user_id));

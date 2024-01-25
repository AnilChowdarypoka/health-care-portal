package com.healthcare.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Appointments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long appointmentId;
	
	private long doctorId;
	
	private long userId;
	
	private Date appointmentDate;
	
	private Date appointmentBookedDate;

	public Appointments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appointments(long appointmentId, long doctorId, long userId, Date appointmentDate,
			Date appointmentBookedDate) {
		super();
		this.appointmentId = appointmentId;
		this.doctorId = doctorId;
		this.userId = userId;
		this.appointmentDate = appointmentDate;
		this.appointmentBookedDate = appointmentBookedDate;
	}

	public long getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(long appointmentId) {
		this.appointmentId = appointmentId;
	}

	public long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(long doctorId) {
		this.doctorId = doctorId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Date getAppointmentBookedDate() {
		return appointmentBookedDate;
	}

	public void setAppointmentBookedDate(Date appointmentBookedDate) {
		this.appointmentBookedDate = appointmentBookedDate;
	}

	@Override
	public String toString() {
		return "Appointments [appointmentId=" + appointmentId + ", doctorId=" + doctorId + ", userId=" + userId
				+ ", appointmentDate=" + appointmentDate + ", appointmentBookedDate=" + appointmentBookedDate + "]";
	}
	
	



}

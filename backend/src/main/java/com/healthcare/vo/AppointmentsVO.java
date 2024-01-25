package com.healthcare.vo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class AppointmentsVO {
	
	private long appointmentId;
	
	private long doctorId;
	
	private long userId;
	
	private String appointmentDate;
	
	private Date appointmentBookedDate;

	public AppointmentsVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppointmentsVO(long appointmentId, long doctorId, long userId, String appointmentDate,
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

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
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

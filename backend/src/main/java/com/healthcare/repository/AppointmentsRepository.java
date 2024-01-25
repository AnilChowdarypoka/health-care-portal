package com.healthcare.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.model.Appointments;

public interface AppointmentsRepository extends JpaRepository <Appointments, Long> {
	
	List<Appointments> findAllByAppointmentDate(Date date);

}

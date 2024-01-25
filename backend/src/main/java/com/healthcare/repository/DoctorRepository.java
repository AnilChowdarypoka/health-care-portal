package com.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.model.Doctors;

public interface DoctorRepository extends JpaRepository <Doctors, Long> {
	
	Doctors findById(long id);
	
	List<Doctors> findByIsActive(boolean check);

}

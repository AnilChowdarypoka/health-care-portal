package com.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.model.Doctors;
import com.healthcare.model.Pharmacy;

public interface PharmacyRepository extends JpaRepository <Pharmacy, Long> {

	Pharmacy findById(long id);

	List<Pharmacy> findByIsActive(boolean b);

}

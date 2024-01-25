package com.healthcare.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.model.Medications;

public interface MedicationsRepository extends JpaRepository <Medications, Long> {

	List<Medications> findAllByEstimatedDeliveryDate(Date date);

}

package com.healthcare.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.healthcare.model.HealthHistory;

public interface HealthHistoryRepository extends JpaRepository<HealthHistory, Long> , CrudRepository<HealthHistory, Long> {
	
	HealthHistory findFirstByUserIdOrderByRecordedAtDesc(long userId);
	

//	List<HealthHistory> findByUserIdOrderByRecordedAtDesc();
	
	List<HealthHistory>  findByUserId (long user_id);



}

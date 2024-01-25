package com.healthcare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.healthcare.model.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

	Users findFirstByUserName(String userName);

	Users findFirstByUserId(long userId);

	
	
}

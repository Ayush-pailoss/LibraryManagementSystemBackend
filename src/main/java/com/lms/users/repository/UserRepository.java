package com.lms.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.users.repository.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
  
	
	public UserEntity findByEmail(String email);
	public UserEntity findByPhoneNo(long phoneNo); 
	public UserEntity findByEmailAndPassword(String email,String password);
	public UserEntity findByEmailAndPhoneNo(String email, long phoneNo);
}
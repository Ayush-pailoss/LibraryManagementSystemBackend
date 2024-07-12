package com.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.repository.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  
	
	public User findByEmail(String email);
	public User findByPhoneNo(long phoneNo); 
	public User findByEmailAndPassword(String email,String password);
	public User findByEmailAndPhoneNo(String email, long phoneNo);
}
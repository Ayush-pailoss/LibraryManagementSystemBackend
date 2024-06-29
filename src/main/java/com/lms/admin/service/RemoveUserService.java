package com.lms.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.lms.admin.controller.response.GlobalResponse;
import com.lms.logs.interfaces.LogInterface;
import com.lms.logs.repository.entity.LogActivityOfUserAndAdmin;
import com.lms.users.repository.UserRepository;
import com.lms.users.repository.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RemoveUserService {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LogInterface logInterface;

	public GlobalResponse removeSelectedUser(@PathVariable String email){
		UserEntity userEntity = userRepository.findByEmail(email);
		if(!(userEntity==null)) {
			userRepository.delete(userEntity);;
			log.info(logInterface.logInfoForUserAndAdmin("user having email "+email+" is removed from database by admin"));
			return new GlobalResponse("user has been removed having email "+email);
		}
		else {
			log.warn(logInterface.logWarnForUserAndAdmin("Admin tried to remove  the user having email "+email+" which does not exist"));
			return new GlobalResponse("no such user is present  having email "+email );
		}
		
		
	}
	} 

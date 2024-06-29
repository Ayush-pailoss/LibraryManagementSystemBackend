package com.lms.users.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.logs.interfaces.LogInterface;
import com.lms.logs.repository.entity.LogActivityOfUserAndAdmin;
import com.lms.users.controller.request.UserLoginRequest;
import com.lms.users.controller.response.UsersGlobalResponse;
import com.lms.users.repository.UserRepository;
import com.lms.users.repository.entity.UserEntity;


@Service
public class UserLoginService {
	
	@Autowired 
	UserRepository userRepository;
	
	@Autowired
	LogInterface logInterface;
	
	Logger logger = LoggerFactory.getLogger(LogActivityOfUserAndAdmin.class);
	public UsersGlobalResponse userLogin(UserLoginRequest userLoginRequest) {
		UserEntity userEntity = userRepository.findByEmailAndPassword(userLoginRequest.getEmail(),userLoginRequest.getPassword());
		if(userEntity==null){
			logger.info(logInterface.logInfoForUserAndAdmin(" User  tried to login with invalid crediantials"));
			return new UsersGlobalResponse (" invalid crediantials ") ;
		}
		else {
			logger.info(logInterface.logInfoForUserAndAdmin(" "+userEntity.getFullName()+" has logged in successfully "));
		return new UsersGlobalResponse (" "+userEntity.getFullName()+" logged in successfully");
		}	
	}
}

package com.lms.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.admin.controller.response.ErrorResponse;
import com.lms.admin.controller.response.RecevedUserResponse;
import com.lms.logs.interfaces.LogInterface;
import com.lms.users.repository.UserRepository;
import com.lms.users.repository.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class FindUserService {
	
	@Autowired
	UserRepository userRepository ; 
	
	@Autowired
	LogInterface logInterface;
	
	public ResponseEntity<?>  findUser(String email) {
	UserEntity userEntity = userRepository.findByEmail(email);
	if(!(userEntity==null)) {
        RecevedUserResponse response = new RecevedUserResponse(userEntity.getId(),userEntity.getFullName(),userEntity.getPhoneNo(),userEntity.getEmail());
		log.info(logInterface.logInfoForUserAndAdmin(" admin found the details of  user having Email "+email));
		return ResponseEntity.ok(response);
	}
	else {
		ErrorResponse errorResponse = new ErrorResponse("user does not exist having email "+email,HttpStatus.BAD_REQUEST.value());
		log.warn(logInterface.logWarnForUserAndAdmin("admin unable to  found the details of  user having Email "+email+".Maybe admin entered invalid email"));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse) ;
	}	
		
	}
}

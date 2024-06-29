package com.lms.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.admin.controller.response.GlobalResponse;
import com.lms.logs.interfaces.LogInterface;
import com.lms.users.controller.request.UserUpdateProfileRequest;
import com.lms.users.repository.UserRepository;
import com.lms.users.repository.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UsersUpdateProfile {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LogInterface logInterface;

	public GlobalResponse updateUserProfile(UserUpdateProfileRequest updateProfileRequest) {
		UserEntity userEntity = userRepository.findByEmail(updateProfileRequest.getEmail());
		if (userEntity != null) {
			userEntity.setFullName(updateProfileRequest.getFullName());
			userEntity.setEmail(updateProfileRequest.getEmail());
			userEntity.setPhoneNo(updateProfileRequest.getPhoneNo());
			userEntity.setPassword(updateProfileRequest.getPassword());
			userRepository.save(userEntity);
			log.info(logInterface.logInfoForUserAndAdmin("user's profile has been updated having email "+updateProfileRequest.getEmail()));
	        return new GlobalResponse("users profile has been updated ");	
		}
		else {
			log.warn(logInterface.logWarnForUserAndAdmin("Profile can not be updated,no such user exist with email "+updateProfileRequest.getEmail()));
	        return new GlobalResponse("profile can not be updated because user does not exists");	
		}
	}

}

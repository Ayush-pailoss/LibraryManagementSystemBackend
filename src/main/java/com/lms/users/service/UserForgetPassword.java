 package com.lms.users.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.logs.interfaces.LogInterface;
import com.lms.logs.repository.LogRepositoryForUserAndAdmin;
import com.lms.users.controller.request.ForgetPasswordRequest;
import com.lms.users.controller.response.UsersGlobalResponse;
import com.lms.users.repository.UserRepository;
import com.lms.users.repository.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service 
public class UserForgetPassword {
	
	@Autowired
	UserRepository  userRepository;
	
	@Autowired
	LogInterface logInterface;
	
	@Autowired
	LogRepositoryForUserAndAdmin logRepository;
	 
	public UsersGlobalResponse newPasswordSetUp(ForgetPasswordRequest forgetPassword) {
//		LogActivityOfUserAndAdmin activityOfUserAndAdmin = new  LogActivityOfUserAndAdmin();
		UserEntity userEntity =userRepository.findByEmail(forgetPassword.getEmail()); 
		if (userEntity == null) {
//    log.error("user entity does't exist for mail {}",forgetPassword.getEmail());
//    activityOfUserAndAdmin.setMessage("user entity does't exist for mail"+forgetPassword.getEmail());
//    LocalDateTime dateTime = LocalDateTime.now();
//    String currentDateTime = dateTime.toString();
//    activityOfUserAndAdmin.setDateTime(currentDateTime);
//    activityOfUserAndAdmin.setLevel("Error");
//    logRepository.save(activityOfUserAndAdmin);
//    log.info("Saved in logRepositoryBooks. Email: {}, currentTime: {}",forgetPassword.getEmail(),activityOfBooks.getDateTime());
		log.warn(logInterface.logWarnForUserAndAdmin("user entity does't exist for mail "+forgetPassword.getEmail()));
		return new  UsersGlobalResponse("user entity does't exist for mail"+forgetPassword.getEmail());
		}
		if (forgetPassword.getNewPassword().equals(forgetPassword.getConfirmPassword())) {
			userEntity.setPassword(forgetPassword.getConfirmPassword());
			userRepository.save(userEntity);
			
			log.info(logInterface.logInfoForUserAndAdmin("password has changed successfully for user having email "+forgetPassword.getEmail()));
			return new  UsersGlobalResponse("password changed successfully ");
		}

		else {
			log.info(logInterface.logInfoForUserAndAdmin("forget password request is not completed"+forgetPassword.getEmail()));
			return new  UsersGlobalResponse("something went wrong while changing the password");
			
		}
	}
}


package com.lms.users.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.logs.interfaces.LogInterface;
import com.lms.users.controller.request.UserRegisterationRequest;
import com.lms.users.controller.response.UsersGlobalResponse;
import com.lms.users.repository.UserRepository;
import com.lms.users.repository.entity.UserEntity;

@Service
public class UsersRegisterationService {

	@Autowired
	UserRepository repository;

	@Autowired
	LogInterface logInterface; 

//	USER SIGN UP SERVICE
	Logger logger = LoggerFactory.getLogger(UserEntity.class);

	public UsersGlobalResponse saveUser(UserRegisterationRequest userRegistrationRequest) {
		UserEntity userEntity = repository.findByEmailAndPhoneNo(userRegistrationRequest.getEmail(),userRegistrationRequest.getPhoneNo());

        if(userEntity==null) {
        	UserEntity emailCheck = repository.findByEmail(userRegistrationRequest.getEmail());
    		UserEntity phoneNoCheck = repository.findByPhoneNo(userRegistrationRequest.getPhoneNo());
        	
    		if (emailCheck != null) {
    			logger.info(logInterface.logInfoForUserAndAdmin("User with this "+emailCheck+" already exists"));
    			return new UsersGlobalResponse("User with this email already exists.");
    		}
    		if (phoneNoCheck != null) {
    			logger.info(logInterface.logInfoForUserAndAdmin("User with this "+phoneNoCheck+" already exists"));
    			return new UsersGlobalResponse("User with this phone number already exists.");
    		}
    		
        	UserEntity user2 = UserEntity.builder().fullName(userRegistrationRequest.getFullName())
    				.email(userRegistrationRequest.getEmail()).phoneNo(userRegistrationRequest.getPhoneNo())
    				.password(userRegistrationRequest.getPassword()).build();
    		repository.save(user2);
    		logger.info(logInterface.logInfoForUserAndAdmin("a new user "+userRegistrationRequest.getFullName() +" has registered "));
    		return new UsersGlobalResponse("user registered successfully");
      }		

//		EMPTY FIELDS CHECK 
//		if((userRegistrationRequest.getFullName()==null)||(userRegistrationRequest.getEmail()==null)||(userRegistrationRequest.getPassword()==null)) {
//			logger.info(logInterface.logInfo("registration fields were not filled properly"));
//			return new UsersResponse("while registration some necessary fields were empty");
//		}
        else {		
          	logger.info(logInterface.logInfoForUserAndAdmin(" email "+userRegistrationRequest.getEmail()+" and "+userRegistrationRequest.getPhoneNo()+" already exists"));
        			return new UsersGlobalResponse("User with this email or phone number already exist ");       
        }
        }

}

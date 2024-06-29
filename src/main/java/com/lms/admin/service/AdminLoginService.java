package com.lms.admin.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.admin.controller.request.AdminLoginRequest;
import com.lms.admin.controller.response.AdminLoginResponse;
import com.lms.admin.repository.AdminRepository;
import com.lms.admin.repository.entity.AdminEntity;
import com.lms.logs.interfaces.LogInterface;
import com.lms.logs.repository.entity.LogActivityOfUserAndAdmin;
import com.lms.logs.service.LogMessageService;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class AdminLoginService {
	@Autowired 
	AdminRepository repository;
	
	@Autowired
	LogInterface logInterface;
 
// ADMIN LOGIN SERVICE	
	public AdminLoginResponse adminLogin(AdminLoginRequest adminLoginRequest) {
		AdminEntity adminEntity = repository.getByEmailAndPassword(adminLoginRequest.getEmail(),adminLoginRequest.getPassword());
		if(adminEntity==null){
			log.warn(logInterface.logWarnForUserAndAdmin("Admin tried to login with invalid crediantials"));
			return new AdminLoginResponse("invalid crediantials enter by admin") ;
		}
		else {
			log.info(logInterface.logInfoForUserAndAdmin("admin  logged in successfully "));
		return new AdminLoginResponse("admin logged in successfully");
		}
	}
}
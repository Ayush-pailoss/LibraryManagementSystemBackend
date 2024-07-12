package com.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.controller.request.BorrowBookRequest;
import com.lms.controller.response.GlobalResponse;
import com.lms.controller.response.UsersGlobalResponse;
import com.lms.service.interfaces.IUserService;
import com.lms.controller.request.ReturnBookRequest;
import com.lms.controller.request.ForgetPasswordRequest;
import com.lms.controller.request.UserLoginRequest;
import com.lms.controller.request.UserRegisterationRequest;
import com.lms.controller.request.UserUpdateProfileRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private  IUserService userService;
	
	
	@PostMapping("/register")
	public UsersGlobalResponse newRegisteration(@Valid @RequestBody UserRegisterationRequest registerationRequest) {
		return userService.saveUser(registerationRequest);
	}

	@PostMapping("/login")
	public UsersGlobalResponse userLogging(@RequestBody UserLoginRequest userLoginRequest) {
		return userService.userLogin(userLoginRequest);
	}

	@PostMapping("/borrowBooks")
	public UsersGlobalResponse borrowNewBook(@RequestBody BorrowBookRequest bookRequest) {
		return userService.borrowBook(bookRequest);
	}

	@DeleteMapping("/returnBook")
	public UsersGlobalResponse returnTheBook(@RequestBody ReturnBookRequest bookRequest) {
		return userService.returnBook(bookRequest);
	}
	@PostMapping("/forgetPass")
	public UsersGlobalResponse forgetPass(@RequestBody ForgetPasswordRequest forgetPasswordRequest) {
		return userService.newPasswordSetUp(forgetPasswordRequest);
		
	}
	@PostMapping("/updateProfile")
	public GlobalResponse updateProfile(@RequestBody UserUpdateProfileRequest userUpdateProfileRequest) {
		return userService.updateUserProfile(userUpdateProfileRequest) ;
		
	}
	
}
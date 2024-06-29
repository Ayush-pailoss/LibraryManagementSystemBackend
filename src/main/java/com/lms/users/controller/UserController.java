package com.lms.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.admin.controller.response.GlobalResponse;
import com.lms.users.controller.request.ReturnBookRequest;
import com.lms.users.controller.request.BorrowBookRequest;
import com.lms.users.controller.request.ForgetPasswordRequest;
import com.lms.users.controller.request.UserLoginRequest;
import com.lms.users.controller.request.UserRegisterationRequest;
import com.lms.users.controller.request.UserUpdateProfileRequest;
import com.lms.users.controller.response.UsersGlobalResponse;
import com.lms.users.service.UserBorrowBookService;
import com.lms.users.service.UserForgetPassword;
import com.lms.users.service.UserLoginService;
import com.lms.users.service.UserReturnBookService;
import com.lms.users.service.UsersRegisterationService;
import com.lms.users.service.UsersUpdateProfile;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UsersRegisterationService userService;

	@Autowired
	UserLoginService userLoginService;
	@Autowired
	UserBorrowBookService borrowedBookService;
	@Autowired
	UserReturnBookService userReturnBookService;
	@Autowired
	UserForgetPassword userForgetPassword; 
	@Autowired
	UsersUpdateProfile uProfile;

	@PostMapping("/register")
	public UsersGlobalResponse newRegisteration(@Valid @RequestBody UserRegisterationRequest registerationRequest) {
		return userService.saveUser(registerationRequest);
	}

	@PostMapping("/login")
	public UsersGlobalResponse userLogging(@RequestBody UserLoginRequest userLoginRequest) {
		return userLoginService.userLogin(userLoginRequest);
	}

	@PostMapping("/borrowBooks")
	public UsersGlobalResponse borrowNewBook(@RequestBody BorrowBookRequest bookRequest) {
		return borrowedBookService.borrowBook(bookRequest);
	}

	@DeleteMapping("/returnBook")
	public UsersGlobalResponse returnTheBook(@RequestBody ReturnBookRequest bookRequest) {
		return userReturnBookService.returnBook(bookRequest);
	}
	@PostMapping("/forgetPass")
	public UsersGlobalResponse forgetPass(@RequestBody ForgetPasswordRequest forgetPasswordRequest) {
		return userForgetPassword.newPasswordSetUp(forgetPasswordRequest);
		
	}
	@PostMapping("/updateProfile")
	public GlobalResponse updateProfile(@RequestBody UserUpdateProfileRequest userUpdateProfileRequest) {
		return uProfile.updateUserProfile(userUpdateProfileRequest) ;
		
	}
	
}
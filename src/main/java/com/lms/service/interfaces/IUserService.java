package com.lms.service.interfaces;

import com.lms.controller.request.BorrowBookRequest;
import com.lms.controller.request.ForgetPasswordRequest;
import com.lms.controller.request.ReturnBookRequest;
import com.lms.controller.request.UserLoginRequest;
import com.lms.controller.request.UserRegisterationRequest;
import com.lms.controller.request.UserUpdateProfileRequest;
import com.lms.controller.response.GlobalResponse;
import com.lms.controller.response.UsersGlobalResponse;

public interface IUserService {
	public UsersGlobalResponse userLogin(UserLoginRequest userLoginRequest);
	
	public UsersGlobalResponse saveUser(UserRegisterationRequest userRegistrationRequest);
	
	public GlobalResponse updateUserProfile(UserUpdateProfileRequest updateProfileRequest);
	
	public UsersGlobalResponse newPasswordSetUp(ForgetPasswordRequest forgetPassword) ;
	
	public UsersGlobalResponse borrowBook(BorrowBookRequest bookRequest) ;
	
	public UsersGlobalResponse returnBook(ReturnBookRequest returnBookRequest) ;

	
	
	
	
	
	
}

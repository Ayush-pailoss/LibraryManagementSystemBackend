package com.lms.service.interfaces;

import org.springframework.http.ResponseEntity;
import com.lms.controller.request.AddBooksRequest;
import com.lms.controller.request.AdminLoginRequest;
import com.lms.controller.response.AdminLoginResponse;
import com.lms.controller.response.GlobalResponse;

public interface IAdminService {
	public GlobalResponse saveBooks(AddBooksRequest addBooksRequest);
	
	public  AdminLoginResponse adminLogin(AdminLoginRequest adminLoginRequest) ;
	
	public ResponseEntity<?>  findUser(String email) ;
		
	public GlobalResponse removeBook(int bookId) ;
	
	public GlobalResponse removeSelectedUser(String email);

}

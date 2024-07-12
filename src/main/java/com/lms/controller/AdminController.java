package com.lms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.controller.request.AddBooksRequest;
import com.lms.controller.request.AdminLoginRequest;
import com.lms.controller.response.AdminLoginResponse;
import com.lms.controller.response.GlobalResponse;
import com.lms.service.interfaces.IAdminService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

//	@Autowired
//	 AdminLoginService adminService;
//	@Autowired
//	 AddingBooksService addingBooksService;
//	@Autowired
//	 FindBookService findBookService;
//	@Autowired
//	 FindUserService findUserService;
//	@Autowired
//	 RemoveBookService removeBookService;
//	@Autowired
//	RemoveUserService removeUserService;

	private final IAdminService adminService;

	public AdminController(IAdminService adminService) {
		this.adminService = adminService;

	}

//  ADMIN LOGIN CONTROLLER	
	@PostMapping("/login")
	public AdminLoginResponse adminLogging(@RequestBody AdminLoginRequest adminLoginRequest) {
		return adminService.adminLogin(adminLoginRequest);
	}

//  ADD BOOKS CONTROLLER
	@PostMapping("/addbooks")
	public GlobalResponse addingBooksByAdmin(@Valid @RequestBody AddBooksRequest addBooksRequest) {
		return adminService.saveBooks(addBooksRequest);

	}

//	FIND USER BY EMAIL & PHONENO. CONTROLLER
	@GetMapping("/user/{email}")
	public ResponseEntity<?> findingUserByEmail(@PathVariable String email) {
		return adminService.findUser(email);

	}

//	REMOVE BOOK BY BOOKID CONTROLLER
	@DeleteMapping("/book/{bookId}")
	public GlobalResponse removeBookFromLibrary(@PathVariable int bookId) {
		return adminService.removeBook(bookId);
	}

//	REMOVE USER BY EMAIL CONTROLLER
	@DeleteMapping("/user/{email}")
	public GlobalResponse removeUserFromDatabase(@PathVariable String email) {
		return adminService.removeSelectedUser(email);
	}
}

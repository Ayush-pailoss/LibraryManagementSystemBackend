package com.lms.admin.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lms.admin.controller.request.AddBooksRequest;
import com.lms.admin.controller.request.AdminLoginRequest;
import com.lms.admin.controller.response.AdminLoginResponse;
import com.lms.admin.controller.response.GlobalResponse;
import com.lms.admin.service.AddingBooksService;
import com.lms.admin.service.AdminLoginService;
import com.lms.admin.service.FindBookService;
import com.lms.admin.service.FindUserService;
import com.lms.admin.service.RemoveBookService;
import com.lms.admin.service.RemoveUserService;

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
	
	    private final AdminLoginService adminService;
	    private final AddingBooksService addingBooksService;
	    private final FindBookService findBookService;
	    private final FindUserService findUserService;
	    private final RemoveBookService removeBookService;
	    private final RemoveUserService removeUserService;

	    public AdminController(AdminLoginService adminService,
	                           AddingBooksService addingBooksService,
	                           FindBookService findBookService,
	                           FindUserService findUserService,
	                           RemoveBookService removeBookService,
	                           RemoveUserService removeUserService) {
	        this.adminService = adminService;
	        this.addingBooksService = addingBooksService;
	        this.findBookService = findBookService;
	        this.findUserService = findUserService;
	        this.removeBookService = removeBookService;
	        this.removeUserService = removeUserService;
	    }
	
	
//  ADMIN LOGIN CONTROLLER	
	@PostMapping("/login")
	public  AdminLoginResponse adminLogging(@RequestBody AdminLoginRequest adminLoginRequest) {
		return adminService.adminLogin(adminLoginRequest);
	}

//  ADD BOOKS CONTROLLER
	@PostMapping("/addbooks")
	public GlobalResponse addingBooksByAdmin(@Valid @RequestBody AddBooksRequest addBooksRequest) {
		return addingBooksService.saveBooks(addBooksRequest) ;
	
	}
//	FIND BOOK BY NAME CONTROLLER
	@GetMapping("/books/{bookName}")
	public ResponseEntity<?> findingBookByBookName(@PathVariable String bookName){
		return findBookService.findBooksByName(bookName);
	}
//	FIND USER BY EMAIL & PHONENO. CONTROLLER
	@GetMapping("/user/{email}")
	public ResponseEntity<?> findingUserByEmail(@PathVariable String email){
		return findUserService.findUser(email);
		
	}
//	REMOVE BOOK BY BOOKID CONTROLLER
	@DeleteMapping("/book/{bookId}")
	public GlobalResponse removeBookFromLibrary(@PathVariable int bookId) {
		return removeBookService.removeBook(bookId);
	}
//	REMOVE USER BY EMAIL CONTROLLER
	@DeleteMapping("/user/{email}")
	public GlobalResponse removeUserFromDatabase(@PathVariable String email) {
		return removeUserService.removeSelectedUser(email);
	}
}













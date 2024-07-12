package com.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.controller.request.AddBooksRequest;
import com.lms.controller.request.AdminLoginRequest;
import com.lms.controller.response.AdminLoginResponse;
import com.lms.controller.response.ErrorResponse;
import com.lms.controller.response.GlobalResponse;
import com.lms.controller.response.RecevedUserResponse;
import com.lms.logs.interfaces.LogInterface;
import com.lms.repository.AdminRepository;
import com.lms.repository.BooksRepository;
import com.lms.repository.UserRepository;
import com.lms.repository.entity.Admin;
import com.lms.repository.entity.Books;
import com.lms.repository.entity.User;
import com.lms.service.interfaces.IAdminService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminServiceImplementation implements IAdminService {
	
	@Autowired
    BooksRepository booksRepository;
	@Autowired
	LogInterface logInterface;
	@Autowired 
	AdminRepository repository;
	@Autowired
	UserRepository userRepository ; 
	
	
	
//	ADDING BOOK SERVICE
	@Override
	public GlobalResponse saveBooks(AddBooksRequest addBooksRequest) {
		Books books = Books.builder().bookId(addBooksRequest.getBookId()).bookName(addBooksRequest.getBookName())
				.authorsName(addBooksRequest.getAuthorsName())
				.description(addBooksRequest.getDescription())
				.noOfBooks(addBooksRequest.getNoOfBooks())
				.build();		         
		if(books!=null) {  
	         booksRepository.save(books);
	 log.info(logInterface.logInfoForBooks("book is added to library"));
		return new GlobalResponse("book saved successfully")   ;
		}
		else {
			 log.warn(logInterface.logWarnForBooks("admin tried to add empty book list "));
				return new GlobalResponse("no books are stored in library by admin")   ;
		}
	}
// ADMIN LOGIN SERVICE
	@Override
	public AdminLoginResponse adminLogin(AdminLoginRequest adminLoginRequest) {
		Admin adminEntity = repository.getByEmailAndPassword(adminLoginRequest.getEmail(),adminLoginRequest.getPassword());
		if(adminEntity==null){
			log.warn(logInterface.logWarnForUserAndAdmin("Admin tried to login with invalid crediantials"));
			return new AdminLoginResponse("invalid crediantials enter by admin") ;
		}
		else {
			log.info(logInterface.logInfoForUserAndAdmin("admin  logged in successfully "));
		return new AdminLoginResponse("admin logged in successfully");
		}
	}
// FIND USER SERVICE
	@Override
	public ResponseEntity<?> findUser(String email) {
		User userEntity = userRepository.findByEmail(email);
		if(!(userEntity==null)) {
	        RecevedUserResponse response = new RecevedUserResponse(userEntity.getId(),userEntity.getFullName(),userEntity.getPhoneNo(),userEntity.getEmail());
			log.info(logInterface.logInfoForUserAndAdmin(" admin found the details of  user having Email "+email));
			return ResponseEntity.ok(response);
		}
		else {
			ErrorResponse errorResponse = new ErrorResponse("user does not exist having email "+email,HttpStatus.BAD_REQUEST.value());
			log.warn(logInterface.logWarnForUserAndAdmin("admin unable to  found the details of  user having Email "+email+".Maybe admin entered invalid email"));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse) ;
		}	
	}
// REMOVE BOOK SERVICE
	@Override
	public GlobalResponse removeBook(int bookId) {
		Books books = booksRepository.findByBookId(bookId);
		if(!(books==null)) {
			booksRepository.delete(books);
			log.info(logInterface.logInfoForBooks("book with bookid "+bookId+" is removed from library by admin"));
			return new GlobalResponse("book has removed having bookId "+bookId+" by admin" );
		}
		else {
			log.warn(logInterface.logWarnForBooks("Admin tried to remove the Book with BookId "+bookId+" which  does not exist in library"));
			return new GlobalResponse("no such book is present  having bookId "+bookId );
		}
	}
// REMOVE USER SERVICE
	@Override
	public GlobalResponse removeSelectedUser(String email) {
		User userEntity = userRepository.findByEmail(email);
		if(!(userEntity==null)) {
			userRepository.delete(userEntity);;
			log.info(logInterface.logInfoForUserAndAdmin("user having email "+email+" is removed from database by admin"));
			return new GlobalResponse("user has been removed having email "+email);
		}
		else {
			log.warn(logInterface.logWarnForUserAndAdmin("Admin tried to remove  the user having email "+email+" which does not exist"));
			return new GlobalResponse("no such user is present  having email "+email );
		}
	}

}

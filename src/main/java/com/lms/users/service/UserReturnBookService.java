package com.lms.users.service;

import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.books.entity.Books;
import com.lms.books.repository.BooksRepository;
import com.lms.logs.interfaces.LogInterface;
import com.lms.users.controller.request.ReturnBookRequest;
import com.lms.users.controller.response.UsersGlobalResponse;
import com.lms.users.repository.UserRepository;
import com.lms.users.repository.entity.UserEntity;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserReturnBookService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BooksRepository booksRepository;

	@Autowired
	private LogInterface logInterface;

	@Transactional
	public UsersGlobalResponse returnBook(ReturnBookRequest returnBookRequest) {
		UserEntity userEntity = userRepository.findByEmail(returnBookRequest.getEmail());
		if (userEntity == null) {
			log.warn(logInterface.logWarnForBooks(
					"user having email " + returnBookRequest.getEmail() + " does not exists needs to get registered"));
			return new UsersGlobalResponse("entered invalid email id");
			}
		
			List<Books> booksToReturnList = userEntity.getBookList().stream()
					.filter(book -> returnBookRequest.getBookId().contains(book.getBookId())).collect(Collectors.toList());
			
			
			if (!booksToReturnList.isEmpty()) {
				for(Books book : booksToReturnList){
					Books libraryBook = booksRepository.findByBookId(book.getBookId());
					if(libraryBook==null) {
						log.warn(logInterface.logWarnForBooks("this book is not borrowed from this library"));
				return new UsersGlobalResponse("this book is not borrowed from this library");

					}
				int updateNumberOfBooks = booksRepository.findByBookId(book.getBookId()).getNoOfBooks();
				updateNumberOfBooks++;
				book.setNoOfBooks(updateNumberOfBooks);
				booksRepository.save(book);
				userEntity.getBookList().remove(book);
				userRepository.save(userEntity);

				log.info(logInterface.logInfoForBooks(" book having book id " + returnBookRequest.getBookId()
						+ " is  returned by user having email " + returnBookRequest.getEmail()));
				return new UsersGlobalResponse("user returned the book ");
			}
				}
			else {
	            
	            log.warn(logInterface.logWarnForBooks("User with email " + returnBookRequest.getEmail() + " did not borrow the book with id " + returnBookRequest.getBookId()));
	            return new UsersGlobalResponse("User never borrowed these books with id's " + returnBookRequest.getBookId());
	        }
			
				log.warn(logInterface.logWarnForBooks(" book having book id " + returnBookRequest.getBookId()
						+ " is not returned  by user having email " + returnBookRequest.getEmail()));
				return new UsersGlobalResponse("user didn't returned the book");
	
			
	}
}

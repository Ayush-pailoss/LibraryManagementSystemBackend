package com.lms.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.admin.controller.request.AddBooksRequest;
import com.lms.admin.controller.response.GlobalResponse;
import com.lms.books.entity.Books;
import com.lms.books.repository.BooksRepository;
import com.lms.logs.interfaces.LogInterface;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class AddingBooksService {
	@Autowired
    BooksRepository booksRepository;
	@Autowired
	LogInterface logInterface;

// ADD BOOK SERVICE
	
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
}

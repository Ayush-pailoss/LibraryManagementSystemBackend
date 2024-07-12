package com.lms.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lms.controller.response.ErrorResponse;
import com.lms.logs.interfaces.LogInterface;
import com.lms.logs.repository.entity.BooksLogs;
import com.lms.repository.BooksRepository;
import com.lms.repository.entity.Books;
import com.lms.service.interfaces.IBookService;
@Service
public class BooksServiceImplementation implements IBookService {

	@Autowired
	BooksRepository booksRepository;
	
	@Autowired
	LogInterface logInterface;
	
	@Override
	public ResponseEntity<?> findBooksByName(String bookName) {
		Logger logger = LoggerFactory.getLogger(BooksLogs.class);
		List<Books> books = booksRepository.findByBookName(bookName);
		if(books.isEmpty()|| books==null) {
			ErrorResponse errorResponse = new ErrorResponse("no such book found with the name "+bookName, HttpStatus.BAD_REQUEST.value());
			logger.warn(logInterface.logWarnForBooks("admin didn't find the  book "+bookName));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
			}
		else {
			logger.info(logInterface.logInfoForBooks("admin found the book "+bookName));
			return ResponseEntity.ok(books);
		}
	}

	@Override
	public ResponseEntity<?> getBookList() {
		return null;
	}

}

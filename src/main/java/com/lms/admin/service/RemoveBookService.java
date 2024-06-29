package com.lms.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.admin.controller.response.GlobalResponse;
import com.lms.books.entity.Books;
import com.lms.books.repository.BooksRepository;
import com.lms.logs.interfaces.LogInterface;
import com.lms.logs.repository.entity.LogActivityOfBooks;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RemoveBookService {
	
	@Autowired 
	BooksRepository booksRepository;
	
	@Autowired
	LogInterface logInterface;
    
@Transactional
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
}

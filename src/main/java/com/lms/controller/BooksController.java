package com.lms.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.lms.service.interfaces.IBookService;

@RestController
public class BooksController {
	
	private final IBookService bookService;
	
	
	public BooksController(IBookService bookService) {
		this.bookService = bookService;
	} 
	
//	FIND BOOK BY NAME CONTROLLER
	@GetMapping("/books/{bookName}")
	public ResponseEntity<?> findingBookByBookName(@PathVariable String bookName){
		return bookService.findBooksByName(bookName);
	}
	
	
	
}

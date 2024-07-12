package com.lms.service.interfaces;

import org.springframework.http.ResponseEntity;

public interface IBookService {
	
	public ResponseEntity<?> findBooksByName(String bookName);
	
	public ResponseEntity<?> getBookList();
}

package com.lms.users.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lms.books.entity.Books;
import com.lms.books.repository.BooksRepository;
import com.lms.logs.interfaces.LogInterface;
import com.lms.logs.repository.entity.LogActivityOfBooks;
import com.lms.users.controller.request.BorrowBookRequest;
import com.lms.users.controller.request.ReturnBookRequest;
import com.lms.users.controller.response.UsersGlobalResponse;
import com.lms.users.repository.UserRepository;
import com.lms.users.repository.entity.UserEntity;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class UserBorrowBookService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	LogInterface logInterface;
	
	@Autowired
	BooksRepository booksRepository;
	
	public UsersGlobalResponse borrowBook(BorrowBookRequest bookRequest) {
		UserEntity userEntity= userRepository.findByEmail(bookRequest.getEmail());
		
		if (userEntity == null) {
            log.warn(logInterface.logWarnForBooks("User with email " + bookRequest.getEmail() + " not found"));
            return new UsersGlobalResponse("User not found");
        }
		
		List<Books> books = booksRepository.findByBookNameIn(bookRequest.getBorrowBooks());

		if((books == null || books.isEmpty())) {
			log.warn(logInterface.logWarnForBooks(bookRequest.getBorrowBooks()+" are not available"));
		    return new UsersGlobalResponse("these  books are not available or you have entered wrong book name and authors name");
		}
		 List<Books> alreadyBorrowedBooks = userEntity.getBookList().stream()
	                .filter(books::contains)
	                .collect(Collectors.toList());
		if(!alreadyBorrowedBooks.isEmpty()) {
			log.warn(logInterface.logWarnForBooks(bookRequest.getBorrowBooks()+" these books are  already been borrowed by the same user"));
		    return new UsersGlobalResponse("not allowed to borrow same books to same user");
		}
		
		for(Books book :books  ) {
		if(book.getNoOfBooks()==0) {
			log.info(logInterface.logWarnForBooks(book.getBookName()+" is currently not available"));
		    return new UsersGlobalResponse("this book is currently not available ");
		}
		else {
//			to get the book list of the user
		     List<Books> usersBooks = userEntity.getBookList(); 
		     usersBooks.add(book); 
		     
//		     int updateNumberOfBook= book.getNoOfBooks();
//		     updateNumberOfBook--;
		     book.setNoOfBooks(book.getNoOfBooks()-1);
		     
		     userEntity.setBookList(usersBooks);
		     
		     userRepository.save(userEntity);
		     booksRepository.save(book);
			
             log.info(logInterface.logInfoForBooks("Book " + book.getBookName() + " borrowed successfully by user " + bookRequest.getEmail()));

	}
	
	}
		log.info(logInterface.logInfoForBooks(bookRequest.getBorrowBooks()+" are  borrowed by user having email "+bookRequest.getEmail()));
	    return new UsersGlobalResponse("user borrowed the book(s)");
}
}

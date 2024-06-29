package com.lms.books.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.books.entity.Books;
@Repository 
public interface BooksRepository extends JpaRepository<Books,Integer> {

	public  List<Books> findByBookName(String bookName);
	
	public Books findByBookId(int bookId);
	
	public Books findByBookNameAndAuthorsName(String bookName , String authorName);
	
	public void deleteByBookId(int bookId);
	
	public List<Books> findByBookNameIn(List<String> borrowBook);
}

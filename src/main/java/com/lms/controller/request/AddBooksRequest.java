package com.lms.controller.request;

import lombok.Data;

@Data
public class AddBooksRequest {
	private int bookId;
	private String bookName;
	private String authorsName;
	private String description;
	private int noOfBooks;
}

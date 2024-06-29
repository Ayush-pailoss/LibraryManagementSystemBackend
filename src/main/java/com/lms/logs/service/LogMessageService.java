package com.lms.logs.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.lms.logs.interfaces.LogInterface;
import com.lms.logs.repository.LogRepositoryBooks;
import com.lms.logs.repository.LogRepositoryAdmin;
import com.lms.logs.repository.entity.BooksLogs;
import com.lms.logs.repository.entity.AdminLogs;

@Service
public class LogMessageService implements LogInterface {

	LocalDateTime localDateTime = LocalDateTime.now();
	DateTimeFormatter format = DateTimeFormatter.ofPattern("E, dd-MM-yyyy HH:mm:ss");

	@Autowired
	LogRepositoryAdmin repositoryForUserAndAdmin;

	@Autowired
	LogRepositoryBooks repositoryBooks;

//	OR WE CAN USE MONGO TEMPLETE TO INSERT IN MOMGODATABASE
//  @Autowired
//	MongoTemplate mongoTemplate;

	@Override
	public String logInfoForUserAndAdmin(String message) {
		String formattedDateTime = localDateTime.format(format);
		AdminLogs logMessage = AdminLogs.builder().message(message).level("INFO")
				.dateTime(formattedDateTime).build();
		repositoryForUserAndAdmin.save(logMessage);
		return message;
	}

	@Override
	public String logInfoForBooks(String message) {
		String formattedDateTime = localDateTime.format(format);
		BooksLogs logMessage = BooksLogs.builder().message(message).level("INFO")
				.dateTime(formattedDateTime).build();
		repositoryBooks.save(logMessage);
		return message;
	}

	@Override
	public String logWarnForBooks(String message) {
		String formattedDateTime = localDateTime.format(format);
		BooksLogs logMessage = BooksLogs.builder().message(message).level("WARN")
				.dateTime(formattedDateTime).build();
		repositoryBooks.save(logMessage);
		return message;
	}

	@Override
	public String logWarnForUserAndAdmin(String message) {
		String formattedDateTime = localDateTime.format(format);
	AdminLogs logMessage = AdminLogs.builder().message(message).level("WARN")
				.dateTime(formattedDateTime).build();
		repositoryForUserAndAdmin.save(logMessage);
		return message;
		
	}

}

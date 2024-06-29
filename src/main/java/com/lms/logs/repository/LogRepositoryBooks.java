package com.lms.logs.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lms.logs.repository.entity.BooksLogs;
@Repository
public interface LogRepositoryBooks extends MongoRepository<BooksLogs, Integer> {

}

package com.lms.logs.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lms.logs.repository.entity.LogActivityOfBooks;
@Repository
public interface LogRepositoryBooks extends MongoRepository<LogActivityOfBooks, Integer> {

}

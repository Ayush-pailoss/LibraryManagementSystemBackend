package com.lms.logs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.lms.logs.repository.entity.UserLogs;
@Repository
public interface LogRepositoryUser  extends MongoRepository<UserLogs, Integer>{

}

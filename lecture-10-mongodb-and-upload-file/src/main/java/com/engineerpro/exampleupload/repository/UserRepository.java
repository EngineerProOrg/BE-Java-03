package com.engineerpro.exampleupload.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.engineerpro.exampleupload.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}

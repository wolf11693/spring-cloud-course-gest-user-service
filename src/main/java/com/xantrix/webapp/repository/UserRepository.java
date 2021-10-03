package com.xantrix.webapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.xantrix.webapp.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	public User findByUserId(String UserId);
}

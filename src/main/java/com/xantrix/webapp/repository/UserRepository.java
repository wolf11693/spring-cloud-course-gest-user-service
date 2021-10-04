package com.xantrix.webapp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.xantrix.webapp.entity.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
	
	public Optional<User> findById(String id);
	
	public User findByUsername(String UserId);
}

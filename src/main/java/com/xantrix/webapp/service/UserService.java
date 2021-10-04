package com.xantrix.webapp.service;

import java.util.List;

import com.xantrix.webapp.entity.User;

public interface UserService {

	public List<User> getAll();
	
	public User getById(String id);
	
	public User getByUsername(String username);
	
	public User getByUsernameAndPassword(String username, String password);
	
	public List<User> getByRole(String role);
	
	public User createNew(User user);
	
	public void update(String userIdToUpd, User user);
}
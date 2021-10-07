package com.xantrix.webapp.service;

import java.util.List;

import com.xantrix.webapp.model.User;
import com.xantrix.webapp.service.exception.DeleteException;
import com.xantrix.webapp.service.exception.SaveException;
import com.xantrix.webapp.service.exception.UpdateException;

public interface UserService {

	public List<User> getAll();
	
	public User getById(String id);
	
	public User getByUsername(String username);
	
	public User getByUsernameAndPassword(String username, String password);
	
	public List<User> getByRole(String role);
	
	public User save(User user) throws SaveException;
	
	public void update(String userIdToUpd, User user) throws UpdateException;
	
	public void delete(String idUserToDel) throws DeleteException;
}
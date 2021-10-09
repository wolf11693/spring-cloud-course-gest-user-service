package com.xantrix.webapp.service;

import java.util.List;

import com.xantrix.webapp.model.User;

public interface UserService extends GenericService<User, String> {

	public User getByUsername(String username);

	public User getByUsernameAndPassword(String username, String password);

	public List<User> getByRole(String role);
}
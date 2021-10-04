package com.xantrix.webapp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xantrix.webapp.exception.DeleteUserException;
import com.xantrix.webapp.model.User;
import com.xantrix.webapp.repository.UserRepository;
import com.xantrix.webapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository UserRepository;
	
	@Override
	public List<User> getAll() {
		LOG.info("** getAll - START **");
		List<User> users = this.UserRepository.findAll();
		LOG.info("** getAll - END **");
		return users;
	}

	@Override
	public User getById(String id) {
		LOG.info("** getById - START  id={} **", id);
		User user = this.UserRepository.findById(id).get();
		LOG.info("** getById - END **");
		return user;
	}

	@Override
	public User getByUsername(String username) {
		LOG.info("** getByUsername - START - userId={} **", username);
		User user = this.UserRepository.findByUsername(username);
		LOG.info("** getByUsername - END **");
		return user;
	}

	@Override
	public User getByUsernameAndPassword(String username, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getByRole(String role) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User save(User user) {
		LOG.info("** save - START - user={}**", user);
		User userSaved = this.UserRepository.save(user);
		LOG.info("** save - END **");
	
		return userSaved;
	}

	@Override
	public void update(String userIdToUpd, User user) {
		LOG.info("** update - START - user={}**", user);
		// TODO
		LOG.info("** update - END **");
	}


	@Override
	public void delete(String idUserToDel) throws Exception {
		User userToDel = this.getById(idUserToDel);
		if(userToDel == null) {
			throw new Exception("cannot find user with id: '" + idUserToDel + "'. Maybe not exists");
		}
		
		try {
			this.delete(userToDel);
		} catch (Exception e) {
			LOG.error("delete user={} failed", userToDel);
			throw new DeleteUserException("delete user failed");
		}
	}
	
	private void delete(User user) {
		LOG.info("** delete - START - user={}**", user);
		this.UserRepository.delete(user);
		LOG.info("** delete - END **");
	}

}
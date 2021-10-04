package com.xantrix.webapp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xantrix.webapp.entity.User;
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
	public User getByUserId(String userId) {
		LOG.info("** getByUserId - START - userId={} **", userId);
		User user = this.UserRepository.findByUserId(userId);
		LOG.info("** getByUserId - END **");
		return user;
	}

	@Override
	public User getByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
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
	public User createNew(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(String userIdToUpd, User user) {
		// TODO Auto-generated method stub
		
	}

}

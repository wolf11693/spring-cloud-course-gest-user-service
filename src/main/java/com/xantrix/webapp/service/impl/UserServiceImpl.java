package com.xantrix.webapp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xantrix.webapp.exception.DeleteException;
import com.xantrix.webapp.exception.SaveException;
import com.xantrix.webapp.exception.UpdateException;
import com.xantrix.webapp.model.User;
import com.xantrix.webapp.repository.UserRepository;
import com.xantrix.webapp.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> getAll() {
		LOG.info("** getAll - START **");
		List<User> users = this.userRepository.findAll();
		LOG.info("** getAll - END **");
		return users;
	}

	@Override
	public User getById(String id) {
		LOG.info("** getById - START  id={} **", id);
		if(id == null || StringUtils.trimAllWhitespace(id).isEmpty()) {
			return null;
		}
		User user = this.userRepository.findById(id).get();
		LOG.info("** getById - END **");
		return user;
	}

	@Override
	public User getByUsername(String username) {
		LOG.info("** getByUsername - START - username={} **", username);
		if(username == null || StringUtils.trimAllWhitespace(username).isEmpty()) {
			return null;
		}
		User user = this.userRepository.findByUsername(username);
		LOG.info("** getByUsername - END **");
		return user;
	}

	@Override
	public User getByUsernameAndPassword(String username, String password) {
		LOG.info("** getByUsernameAndPassword - START - username={}, password={} **", username, password);
		LOG.info("** getByUsernameAndPassword - END **");
		return null;
	}

	@Override
	public List<User> getByRole(String role) {
		LOG.info("** getByRole - START - role={} **", role);
		LOG.info("** getByRole - END **");
		return null;
	}
	
	@Override
	public void update(String userIdToUpd, User userDataToUpd) throws UpdateException {
		LOG.info("** update - START - userIdToUpd={}, userDataToUpd={} **", userIdToUpd, userDataToUpd);
		if(userIdToUpd == null || StringUtils.trimAllWhitespace(userIdToUpd).isEmpty()) {
			return ;
		}
		if(userDataToUpd == null) {
			return ;
		}
		
		User userFetched = this.getById(userIdToUpd);
		if(userFetched == null) {
			throw new UpdateException("cannot update user with id: '" + userIdToUpd + "'. Maybe not exists");
		}
		userFetched.setUsername(userDataToUpd.getUsername() != null? userDataToUpd.getUsername(): userFetched.getUsername());
		userFetched.setPassword(userDataToUpd.getPassword() != null? userDataToUpd.getPassword(): userFetched.getPassword());
		userFetched.setRuoli(userDataToUpd.getRuoli() != null? userDataToUpd.getRuoli(): userFetched.getRuoli());
		userFetched.setAttivo(userDataToUpd.getAttivo() != null? userDataToUpd.getAttivo(): userFetched.getAttivo());

		try {
			this.save(userFetched);
		} catch (Exception e) {
			LOG.error("update user={} failed", userFetched);
			throw new UpdateException("update user failed");
		}
		LOG.info("** update - END **");
	}

	@Override
	public User save(User user) throws SaveException {
		LOG.info("** save - START - user={}* *", user);
		if(user == null) {
			throw new SaveException("cannot save null object");
		}
		
		User userSaved = null;
		try {
			userSaved = this.userRepository.save(user);
		} catch (Exception ex) {
			LOG.error("save user={} failed", user);
			throw new SaveException("save user failed");
		}
		LOG.info("** save - END **");
	
		return userSaved;
	}
	
	@Override
	public void delete(String idUserToDel) throws DeleteException {
		if(idUserToDel == null || StringUtils.trimAllWhitespace(idUserToDel).isEmpty()) {
			throw new DeleteException("id user to delete is null or empty string");
		}

		User userToDel = this.getById(idUserToDel);
		if(userToDel == null) {
			throw new DeleteException("cannot delete user with id: '" + idUserToDel + "'. Maybe not exists");
		}
		
		try {
			this.delete(userToDel);
		} catch (Exception e) {
			LOG.error("delete user={} failed", userToDel);
			throw new DeleteException("delete user failed");
		}
	}
	
	private void delete(User user) {
		LOG.info("** delete - START - user={}**", user);
		if(user == null) {
			return ;
		}
		this.userRepository.delete(user);
		LOG.info("** delete - END **");
	}
}
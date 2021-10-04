package com.xantrix.webapp.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xantrix.webapp.entity.User;
import com.xantrix.webapp.resource.UserResource;
import com.xantrix.webapp.resource.UsersResource;
import com.xantrix.webapp.resource.transformer.UserResourceTransformer;
import com.xantrix.webapp.resource.transformer.UsersResourceTransformer;
import com.xantrix.webapp.response.ResponseBody;
import com.xantrix.webapp.service.UserService;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
	private static Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserResourceTransformer userResourceTranformer;
	@Autowired
	private UsersResourceTransformer usersResourceTranformer;

	@GetMapping(path = "/")
	public ResponseEntity<ResponseBody<UsersResource>> getUsers() {
		LOG.info("** GET api/user/ - getUsers - START **");
		List<User> usersFetched = this.userService.getAll();
		UsersResource usersResource = this.usersResourceTranformer.getResourceByModel(usersFetched);
		ResponseBody<UsersResource> responseBody = new ResponseBody<UsersResource>(usersResource);
		LOG.info("** GET api/user/ - getUsers - END **");

		return new ResponseEntity<ResponseBody<UsersResource>>(responseBody, HttpStatus.OK);
	}

	@GetMapping(path = "/{username}")
	public ResponseEntity<ResponseBody<UserResource>> getUserByUsername(
			@PathParam(value = "username") String username) {
		LOG.info("** GET api/user/{} - getUserByUsername - username={} - START **", username);
		User userFetched = this.userService.getByUsername(username);
		UserResource userResource = this.userResourceTranformer.getResourceByModel(userFetched);
		ResponseBody<UserResource> responseBody = new ResponseBody<UserResource>(userResource);
		LOG.info("** GET api/user/{} - getUserByUsername - barcode={}- END **");

		return new ResponseEntity<ResponseBody<UserResource>>(responseBody, HttpStatus.OK);
	}

}
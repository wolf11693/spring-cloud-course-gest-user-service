package com.xantrix.webapp.resource.transformer;

import org.springframework.stereotype.Component;

import com.xantrix.webapp.model.User;
import com.xantrix.webapp.resource.UserResource;

@Component
public class UserResourceTransformer implements ResourceTransfromer<UserResource, User> {

	@Override
	public UserResource transformFrom(User userModel) {
		return this.mapObjModelToResource(userModel);
	}

	private UserResource mapObjModelToResource(User userModel) {
		UserResource userResource = new UserResource();

		userResource
			.setUsername(userModel.getUsername())
			.setAttivo(userModel.getAttivo())
			.setRuoli(userModel.getRuoli());

		return userResource;
	}
}
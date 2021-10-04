package com.xantrix.webapp.resource.transformer;

import java.util.List;

import org.springframework.stereotype.Component;

import com.xantrix.webapp.entity.User;
import com.xantrix.webapp.resource.UsersResource;

@Component
public class UsersResourceTransformer implements ResourceTransfromer<UsersResource, List<User>> {

	@Override
	public UsersResource transformFrom(List<User> userListModel) {
		return this.mapObjModelToResource(userListModel);
	}

	private UsersResource mapObjModelToResource(List<User> userListModel) {
		UsersResource usersResource = new UsersResource(userListModel);

		return usersResource;
	}
}
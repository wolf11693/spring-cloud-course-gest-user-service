package com.xantrix.webapp.resource;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.xantrix.webapp.entity.User;

public class UsersResource implements Serializable, Resource {
	private static final long serialVersionUID = 793131449686916448L;

	@JsonProperty(value = "users")
	List<User> users = new ArrayList<>();

	public UsersResource(List<User> users) {
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UsersResource [users=");
		builder.append(users);
		builder.append("]");
		return builder.toString();
	}

}
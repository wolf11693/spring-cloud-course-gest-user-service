package com.xantrix.webapp.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserInputDto implements Serializable {
	private static final long serialVersionUID = -2739201937160668082L;

	@JsonProperty(value = "username")
	private String username;

	@JsonProperty(value = "password")
	private String password;

	@JsonProperty(value = "ruoli")
	private List<String> ruoli = new ArrayList<>();

	public String getUsername() {
		return username;
	}

	public UserInputDto setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public UserInputDto setPassword(String password) {
		this.password = password;
		return this;
	}

	public List<String> getRuoli() {
		return ruoli;
	}

	public UserInputDto setRuoli(List<String> ruoli) {
		this.ruoli = ruoli;
		return this;
	}
}
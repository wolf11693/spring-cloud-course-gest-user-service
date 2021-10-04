package com.xantrix.webapp.resource;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName(value = "user")
public class UserResource implements Serializable, Resource {
	private static final long serialVersionUID = 793131449686916448L;

	@JsonProperty(value = "username")
	private String username;

	@JsonProperty(value = "attivo")
	private String attivo;

	@JsonProperty(value = "ruoli")
	private List<String> ruoli;

	public String getUsername() {
		return username;
	}

	public UserResource setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getAttivo() {
		return attivo;
	}

	public UserResource setAttivo(String attivo) {
		this.attivo = attivo;
		return this;
	}

	public List<String> getRuoli() {
		return ruoli;
	}

	public UserResource setRuoli(List<String> ruoli) {
		this.ruoli = ruoli;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserResource [username=");
		builder.append(username);
		builder.append(", attivo=");
		builder.append(attivo);
		builder.append(", ruoli=");
		builder.append(ruoli);
		builder.append("]");
		return builder.toString();
	}

}
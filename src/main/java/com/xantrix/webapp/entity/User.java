package com.xantrix.webapp.entity;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user")
public class User implements Serializable {
	private static final long serialVersionUID = 6851143080463377374L;

	@Id
	private String id;

	@Indexed(unique = true)
	@Size(min = 5, max = 80, message = "{Size.Utenti.userId.Validation}")
	@NotNull(message = "{NotNull.Articoli.userId.Validation}")
	private String username;

	@Size(min = 8, max = 80, message = "{Size.Utenti.password.Validation}")
	private String password;

	private String attivo;

	private List<String> ruoli;

	public String getId() {
		return id;
	}

	public User setId(String id) {
		this.id = id;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public String getAttivo() {
		return attivo;
	}

	public User setAttivo(String attivo) {
		this.attivo = attivo;
		return this;
	}

	public List<String> getRuoli() {
		return ruoli;
	}

	public User setRuoli(List<String> ruoli) {
		this.ruoli = ruoli;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", username=");
		builder.append(username);
		builder.append(", password=");
		builder.append(password);
		builder.append(", attivo=");
		builder.append(attivo);
		builder.append(", ruoli=");
		builder.append(ruoli);
		builder.append("]");
		return builder.toString();
	}

}

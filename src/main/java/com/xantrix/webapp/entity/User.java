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
	private String userId;

	@Size(min = 8, max = 80, message = "{Size.Utenti.password.Validation}")
	private String password;

	private String attivo;

	private List<String> ruoli;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAttivo() {
		return attivo;
	}

	public void setAttivo(String attivo) {
		this.attivo = attivo;
	}

	public List<String> getRuoli() {
		return ruoli;
	}

	public void setRuoli(List<String> ruoli) {
		this.ruoli = ruoli;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
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

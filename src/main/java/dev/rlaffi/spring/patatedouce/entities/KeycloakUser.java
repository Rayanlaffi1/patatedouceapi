package dev.rlaffi.spring.patatedouce.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_ENTITY")
public class KeycloakUser {
	@Id
	@Column(name = "ID")
	private String id;

	public KeycloakUser(){}

	public KeycloakUser(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}

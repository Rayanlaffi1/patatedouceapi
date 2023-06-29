package dev.rlaffi.spring.patatedouce.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "KEYCLOAK_ROLE")
public class Role {
	@Id
	@Column(name = "ID")
	private String id;
	@Column(name = "NAME")
	private String nom;
	public Role(){}
	public Role(String id, String nom) {
		this.id = id;
		this.nom = nom;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
}

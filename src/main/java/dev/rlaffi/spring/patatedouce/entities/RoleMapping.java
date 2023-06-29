package dev.rlaffi.spring.patatedouce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "USER_ROLE_MAPPING")
public class RoleMapping {
	@Id
	@ManyToOne
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")
	private Role role;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "USER_ID", referencedColumnName = "ID")
	private Utilisateur utilisateur;
	public RoleMapping(){}

	public RoleMapping(Role role, Utilisateur utilisateur) {
		this.role = role;
		this.utilisateur = utilisateur;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
}

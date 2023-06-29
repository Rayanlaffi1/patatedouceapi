package dev.rlaffi.spring.patatedouce.entities;

import jakarta.persistence.*;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;

@Entity
@Table(name = "USER_ENTITY")
public class Utilisateur {
	@Id
	@Column(name = "ID")
	private String id;
	@Column(name = "FIRST_NAME")
	private String prenom;
	@Column(name = "LAST_NAME")
	private String nom;
	@Column(name = "EMAIL")
	private String email;
	@OneToMany(mappedBy = "utilisateur")
	private List<RoleMapping> roleMappings;

	@OneToMany(mappedBy = "utilisateur")
	private List<PanierAchat> panierAchats;
	public Utilisateur() {
	}

	public Utilisateur(String id, String prenom, String nom, String email, List<RoleMapping> roleMappings, List<PanierAchat> panierAchats) {
		this.id = id;
		this.prenom = prenom;
		this.nom = nom;
		this.email = email;
		this.roleMappings = roleMappings;
		this.panierAchats = panierAchats;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<RoleMapping> getRoleMappings() {
		return roleMappings;
	}

	public void setRoleMappings(List<RoleMapping> roleMappings) {
		this.roleMappings = roleMappings;
	}

	public List<PanierAchat> getPanierAchats() {
		return panierAchats;
	}

	public void setPanierAchats(List<PanierAchat> panierAchats) {
		this.panierAchats = panierAchats;
	}
}

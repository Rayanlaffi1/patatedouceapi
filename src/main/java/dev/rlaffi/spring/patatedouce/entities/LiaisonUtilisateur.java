package dev.rlaffi.spring.patatedouce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "patatedouce_liaisonUtilisateur")
public class LiaisonUtilisateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@OneToOne
	@JoinColumn(name = "utilisateur_id", unique = true)
	private Utilisateur utilisateur;
	@OneToOne
	@JoinColumn(name = "keycloakuser_id", unique = true)
	@JsonIgnore
	private KeycloakUser keycloakuser;
	public LiaisonUtilisateur(){}

	public LiaisonUtilisateur(Utilisateur utilisateur, KeycloakUser keycloakuser) {
		this.utilisateur = utilisateur;
		this.keycloakuser = keycloakuser;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public KeycloakUser getKeycloakuser() {
		return keycloakuser;
	}

	public void setKeycloakuser(KeycloakUser keycloakuser) {
		this.keycloakuser = keycloakuser;
	}
}

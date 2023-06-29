package dev.rlaffi.spring.patatedouce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patatedouce_panierachat")
public class PanierAchat {
	@ManyToOne
	@Id
	@JoinColumn(name = "aliment_id")
	private Aliment aliment;

	@ManyToOne
	@Id
	@JoinColumn(name = "user_id")
	@JsonIgnore
	private Utilisateur utilisateur;
	@Column(name = "qte")
	private Integer qte;
	public PanierAchat(){}

	public PanierAchat(Aliment aliment, Utilisateur utilisateur, Integer qte) {
		this.aliment = aliment;
		this.utilisateur = utilisateur;
		this.qte = qte;
	}

	public Aliment getAliment() {
		return aliment;
	}

	public void setAliment(Aliment aliment) {
		this.aliment = aliment;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Integer getQte() {
		return qte;
	}

	public void setQte(Integer qte) {
		this.qte = qte;
	}
}

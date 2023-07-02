package dev.rlaffi.spring.patatedouce.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "patatedouce_client")
public class Client extends Utilisateur {
	@OneToMany(mappedBy = "client")
	private List<PanierAchat> panierAchats;
	@OneToMany(mappedBy = "client")
	private List<RecetteFavoris> listeRecettesFavoris;
	public Client() {
		super();
	}

	public Client(Long id, String prenom, String nom, String email, List<PanierAchat> panierAchats, List<RecetteFavoris> listeRecettesFavoris) {
		super(id, prenom, nom, email);
		this.panierAchats = panierAchats;
		this.listeRecettesFavoris = listeRecettesFavoris;
	}

	public List<PanierAchat> getPanierAchats() {
		return panierAchats;
	}

	public void setPanierAchats(List<PanierAchat> panierAchats) {
		this.panierAchats = panierAchats;
	}

	public List<RecetteFavoris> getListeRecettesFavoris() {
		return listeRecettesFavoris;
	}

	public void setListeRecettesFavoris(List<RecetteFavoris> listeRecettesFavoris) {
		this.listeRecettesFavoris = listeRecettesFavoris;
	}
}

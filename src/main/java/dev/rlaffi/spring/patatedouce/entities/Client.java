package dev.rlaffi.spring.patatedouce.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "patatedouce_client")
public class Client extends Utilisateur {
	@OneToMany(mappedBy = "client")
	private List<PanierAchat> panierAchats;
	public Client() {
		super();
	}
	public Client(Long id, String prenom, String nom, String email, List<PanierAchat> panierAchats) {
		super(id, prenom, nom, email);
		this.panierAchats = panierAchats;
	}
	public List<PanierAchat> getPanierAchats() {
		return panierAchats;
	}

	public void setPanierAchats(List<PanierAchat> panierAchats) {
		this.panierAchats = panierAchats;
	}
}

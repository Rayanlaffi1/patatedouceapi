package dev.rlaffi.spring.patatedouce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "patatedouce_recettefavoris")
public class RecetteFavoris {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@ManyToOne
	@JoinColumn(name = "recette_id")
	private Recette recette;
	@ManyToOne
	@JoinColumn(name = "client_id")
	@JsonIgnore
	private Client client;
	public RecetteFavoris(){}

	public RecetteFavoris(Recette recette, Client client) {
		this.recette = recette;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Recette getRecette() {
		return recette;
	}

	public void setRecette(Recette recette) {
		this.recette = recette;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
}

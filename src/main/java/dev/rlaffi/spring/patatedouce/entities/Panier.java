package dev.rlaffi.spring.patatedouce.entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotNull;

import java.util.*;

@Entity
@Table(name = "patatedouce_panier")
public class Panier {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String image;
	@OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<AlimentPanier> aliments = new ArrayList<>();
	public Panier(){}

	public Panier(Integer id, String nom, String image, List<AlimentPanier> aliments) {
		this.id = id;
		this.nom = nom;
		this.image = image;
		this.aliments = aliments;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<AlimentPanier> getAliments() {
		return aliments;
	}

	public void setAliments(List<AlimentPanier> aliments) {
		this.aliments = aliments;
	}
}

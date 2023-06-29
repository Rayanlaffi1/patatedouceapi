package dev.rlaffi.spring.patatedouce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patatedouce_recette")
public class Recette {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String image;

	@OneToMany(mappedBy = "recette", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<EtapeRecette> etapes = new ArrayList<>();
	public Recette(){}
	public Recette(Integer id, String nom, String image, List<EtapeRecette> etapes) {
		this.id = id;
		this.nom = nom;
		this.image = image;
		this.etapes = etapes;
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

	public List<EtapeRecette> getEtapes() {
		return etapes;
	}

	public void setEtapes(List<EtapeRecette> etapes) {
		this.etapes = etapes;
	}
}

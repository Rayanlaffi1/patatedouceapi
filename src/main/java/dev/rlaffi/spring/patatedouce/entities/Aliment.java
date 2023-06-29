package dev.rlaffi.spring.patatedouce.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patatedouce_aliment")
public class Aliment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String origine;
	private Float prix;
	private String image;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_typealiment")
	@NotNull
	private TypeAliment typeAliment = new TypeAliment();

	public Aliment(){}

	public Aliment(Integer id, String nom, String origine, Float prix, String image, TypeAliment typeAliment) {
		this.id = id;
		this.nom = nom;
		this.origine = origine;
		this.prix = prix;
		this.image = image;
		this.typeAliment = typeAliment;
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

	public String getOrigine() {
		return origine;
	}

	public void setOrigine(String origine) {
		this.origine = origine;
	}

	public Float getPrix() {
		return prix;
	}

	public void setPrix(Float prix) {
		this.prix = prix;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public TypeAliment getTypeAliment() {
		return typeAliment;
	}

	public void setTypeAliment(TypeAliment typeAliment) {
		this.typeAliment = typeAliment;
	}
}

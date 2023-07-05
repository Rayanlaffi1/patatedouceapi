package dev.rlaffi.spring.patatedouce.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import dev.rlaffi.spring.patatedouce.views.Views;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import net.minidev.json.annotate.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "patatedouce_aliment")
public class Aliment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	private String origine;
	private Float prixut;
	private Integer quantite;
	private String image;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_typealiment")
	@NotNull
	private TypeAliment typeAliment = new TypeAliment();
	@JsonIgnoreProperties("article")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_maraicher")
	@NotNull
	private Maraicher maraicher = new Maraicher();
	public Aliment(){}

	public Aliment(Integer id, String nom, String origine, Float prixut, Integer quantite, String image, TypeAliment typeAliment, Maraicher maraicher) {
		this.id = id;
		this.nom = nom;
		this.origine = origine;
		this.prixut = prixut;
		this.quantite = quantite;
		this.image = image;
		this.typeAliment = typeAliment;
		this.maraicher = maraicher;
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

	public Float getPrixut() {
		return prixut;
	}

	public void setPrixut(Float prixut) {
		this.prixut = prixut;
	}

	public Integer getQuantite() {
		return quantite;
	}

	public void setQuantite(Integer quantite) {
		this.quantite = quantite;
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

	public Maraicher getMaraicher() {
		return maraicher;
	}

	public void setMaraicher(Maraicher maraicher) {
		this.maraicher = maraicher;
	}
}

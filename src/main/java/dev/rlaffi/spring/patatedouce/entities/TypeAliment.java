package dev.rlaffi.spring.patatedouce.entities;

import com.fasterxml.jackson.annotation.JsonView;
import dev.rlaffi.spring.patatedouce.views.Views;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patatedouce_type_aliment")
public class TypeAliment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nom;
	public TypeAliment(){}

	public TypeAliment(Integer id, String nom) {
		this.id = id;
		this.nom = nom;
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
}

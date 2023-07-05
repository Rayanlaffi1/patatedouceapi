package dev.rlaffi.spring.patatedouce.entities;

import com.fasterxml.jackson.annotation.*;
import dev.rlaffi.spring.patatedouce.views.Views;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patatedouce_maraicher")
public class Maraicher extends Utilisateur {
	@JsonIgnoreProperties("maraicher")
	@OneToMany(mappedBy = "maraicher", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Aliment> aliments = new ArrayList<>();
	public Maraicher() {
		super();
	}
	public Maraicher(Long id, String prenom, String nom, String email, List<Aliment> aliments) {
		super(id, prenom, nom, email);
		this.aliments = aliments;
	}

	public List<Aliment> getAliments() {
		return aliments;
	}

	public void setAliments(List<Aliment> aliments) {
		this.aliments = aliments;
	}
}

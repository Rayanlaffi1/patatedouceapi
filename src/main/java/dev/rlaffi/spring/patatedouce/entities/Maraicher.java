package dev.rlaffi.spring.patatedouce.entities;

import jakarta.persistence.*;

import java.util.List;

//@Entity
//@Table(name = "patatedouce_maraicher")
public class Maraicher extends Utilisateur {
	public Maraicher() {
		super();
	}
	public Maraicher(String id, String prenom, String nom, String email, List<RoleMapping> roleMappings, List<PanierAchat> panierAchats) {
		super(id, prenom, nom, email, roleMappings, panierAchats);
	}

}

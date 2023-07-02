package dev.rlaffi.spring.patatedouce.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "patatedouce_maraicher")
public class Maraicher extends Utilisateur {
	@OneToMany(mappedBy = "maraicher")
	private List<StockMaraicher> stockMaraichers;
	public Maraicher() {
		super();
	}
	public Maraicher(Long id, String prenom, String nom, String email, List<StockMaraicher> stockMaraichers) {
		super(id, prenom, nom, email);
		this.stockMaraichers = stockMaraichers;
	}

	public List<StockMaraicher> getStockMaraichers() {
		return stockMaraichers;
	}

	public void setStockMaraichers(List<StockMaraicher> stockMaraichers) {
		this.stockMaraichers = stockMaraichers;
	}
}

package dev.rlaffi.spring.patatedouce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patatedouce_panierachat")
public class PanierAchat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	@ManyToOne
	@JoinColumn(name = "aliment_id")
	private Aliment aliment;
	@ManyToOne
	@JoinColumn(name = "client_id")
	@JsonIgnore
	private Client client;
	@Column(name = "qte")
	private Integer qte;
	public PanierAchat(){}

	public PanierAchat(Aliment aliment, Client client, Integer qte) {
		this.aliment = aliment;
		this.client = client;
		this.qte = qte;
	}

	public Aliment getAliment() {
		return aliment;
	}

	public void setAliment(Aliment aliment) {
		this.aliment = aliment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Integer getQte() {
		return qte;
	}

	public void setQte(Integer qte) {
		this.qte = qte;
	}
}

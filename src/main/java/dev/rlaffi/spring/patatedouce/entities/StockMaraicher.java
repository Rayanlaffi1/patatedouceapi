package dev.rlaffi.spring.patatedouce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.keycloak.representations.idm.UserRepresentation;

@Entity
@Table(name = "patatedouce_stock_maraicher")
public class StockMaraicher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;
    @ManyToOne
    @JoinColumn(name = "aliment_id")
    private Aliment aliment;
    @ManyToOne
    @JoinColumn(name = "maraicher_id")
    @JsonIgnore
    private Maraicher maraicher;
    @Column(name = "quantite")
    private int quantite;
    @Column(name = "prixut")
    private Float prixut;
    public StockMaraicher(){}

    public StockMaraicher(Aliment aliment, Maraicher maraicher, int quantite, Float prixut) {
        this.aliment = aliment;
        this.maraicher = maraicher;
        this.quantite = quantite;
        this.prixut = prixut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aliment getAliment() {
        return aliment;
    }

    public void setAliment(Aliment aliment) {
        this.aliment = aliment;
    }

    public Maraicher getMaraicher() {
        return maraicher;
    }

    public void setMaraicher(Maraicher maraicher) {
        this.maraicher = maraicher;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Float getPrixut() {
        return prixut;
    }

    public void setPrixut(Float prixut) {
        this.prixut = prixut;
    }
}

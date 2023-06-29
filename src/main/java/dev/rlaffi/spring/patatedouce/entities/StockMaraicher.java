package dev.rlaffi.spring.patatedouce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.keycloak.representations.idm.UserRepresentation;

@Entity
@Table(name = "patatedouce_stock_maraicher")
public class StockMaraicher {
    @ManyToOne
    @Id
    @JoinColumn(name = "aliment_id")
    private Aliment aliment;
    @Id
    @Column(name = "utilisateur_id")
    private String utilisateurId;

    @Column(name = "quantite")
    private int quantite;
    public StockMaraicher(){}

    public StockMaraicher(Aliment aliment, String utilisateurId, int quantite) {
        this.aliment = aliment;
        this.utilisateurId = utilisateurId;
        this.quantite = quantite;
    }

    public Aliment getAliment() {
        return aliment;
    }

    public void setAliment(Aliment aliment) {
        this.aliment = aliment;
    }

    public String getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(String utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}

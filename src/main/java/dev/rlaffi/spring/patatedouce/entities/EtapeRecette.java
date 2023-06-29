package dev.rlaffi.spring.patatedouce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "patatedouce_etaperecette")
public class EtapeRecette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "etape_id")
    private Etape etape;

    @ManyToOne
    @JoinColumn(name = "recette_id")
    @JsonIgnore
    private Recette recette;

    @Column(name = "ordre")
    private Integer ordre;

    public EtapeRecette() {}

    public EtapeRecette(Etape etape, Recette recette, Integer ordre) {
        this.etape = etape;
        this.recette = recette;
        this.ordre = ordre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Etape getEtape() {
        return etape;
    }

    public void setEtape(Etape etape) {
        this.etape = etape;
    }

    public Recette getRecette() {
        return recette;
    }

    public void setRecette(Recette recette) {
        this.recette = recette;
    }

    public Integer getOrdre() {
        return ordre;
    }

    public void setOrdre(Integer ordre) {
        this.ordre = ordre;
    }
}

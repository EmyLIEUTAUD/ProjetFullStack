package org.polytech.covid.Entity;

import javax.persistence.*;

@Entity
public class Centre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_centre;
    private String nom;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_ville", referencedColumnName = "id_ville")
    private Ville id_ville;

    public Centre(Integer id_centre, String nom, Ville id_ville) {
        this.id_centre = id_centre;
        this.nom = nom;
        this.id_ville = id_ville;
    }

    public Centre() {

    }

    public Integer getId_centre() {
        return id_centre;
    }

    public void setId_centre(Integer id_centre) {
        this.id_centre = id_centre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Ville getId_ville() {
        return id_ville;
    }

    public void setId_ville(Ville id_ville) {
        this.id_ville = id_ville;
    }
}

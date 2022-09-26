package org.polytech.covid;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ville {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ville;
    private String nom;

    public Ville(Integer id_ville, String nom) {
        this.id_ville = id_ville;
        this.nom = nom;
    }

    public Integer getId_ville() {
        return id_ville;
    }

    public void setId_ville(Integer id_ville) {
        this.id_ville = id_ville;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}

package org.polytech.covid.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Superadmin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_superadmin;

    @OneToOne
    @JoinColumn(name = "identifiant", referencedColumnName = "identifiant")
    private Personne personne;

    public Superadmin(Personne personne) {
        this.personne = personne;
    }

    public Superadmin() {

    }

    public Integer getId_superadmin() {
        return id_superadmin;
    }

    public void setId_superadmin(Integer id_superadmin) {
        this.id_superadmin = id_superadmin;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

}

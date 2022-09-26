package org.polytech.covid;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Public {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_personne;

    @OneToOne
    @JoinColumn(name = "identifiant", referencedColumnName = "identifiant")
    private Personne personne;

    private Integer dose;

    public Public(Integer id_personne, Personne personne, Integer dose) {
        this.id_personne = id_personne;
        this.personne = personne;
        this.dose = dose;
    }

    public Integer getId_personne() {
        return id_personne;
    }

    public void setId_personne(Integer id_personne) {
        this.id_personne = id_personne;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Integer getDose() {
        return dose;
    }

    public void setDose(Integer dose) {
        this.dose = dose;
    }

}

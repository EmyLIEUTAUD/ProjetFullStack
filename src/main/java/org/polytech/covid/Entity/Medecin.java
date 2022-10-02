package org.polytech.covid.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Medecin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_medecin;

    @OneToOne
    @JoinColumn(name = "identifiant", referencedColumnName = "identifiant")
    private Personne personne;

    @OneToOne
    @JoinColumn(name = "gid", referencedColumnName = "gid")
    private Centre centre;

    public Medecin(Personne personne, Centre centre) {
        this.personne = personne;
        this.centre = centre;
    }

    public Medecin() {

    }

    public Integer getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(Integer id_medecin) {
        this.id_medecin = id_medecin;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

}

package org.polytech.covid;

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

    @OneToOne
    @JoinColumn(name = "id_centre", referencedColumnName = "id_centre")
    private Centre id_centre;

    @OneToOne
    @JoinColumn(name = "id_ville", referencedColumnName = "id_ville")
    private Ville id_ville;

    public Superadmin(Integer id_superadmin, Personne personne, Centre id_centre, Ville id_ville) {
        this.id_superadmin = id_superadmin;
        this.personne = personne;
        this.id_centre = id_centre;
        this.id_ville = id_ville;
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

    public Centre getId_centre() {
        return id_centre;
    }

    public void setId_centre(Centre id_centre) {
        this.id_centre = id_centre;
    }

    public Ville getId_ville() {
        return id_ville;
    }

    public void setId_ville(Ville id_ville) {
        this.id_ville = id_ville;
    }

}

package org.polytech.covid.Entity;

import java.time.LocalDate;

import javax.persistence.*;

@Entity
public class Reservation {

    /***
     * Table contenant les réservations
     ***/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_reservation;
    private LocalDate date_reservation;

    @ManyToOne
    @JoinColumn(name = "identifiant", referencedColumnName = "identifiant")
    private Personne personne;

    @ManyToOne
    @JoinColumn(name = "gid", referencedColumnName = "gid")
    private Centre centre;

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Reservation() {

    }

    public Reservation(LocalDate date_reservation, Personne personne, Centre centre) {
        this.date_reservation = date_reservation;
        this.personne = personne;
        this.centre = centre;
    }

    public Integer getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(Integer id_reservation) {
        this.id_reservation = id_reservation;
    }

    public LocalDate getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(LocalDate date_reservation) {
        this.date_reservation = date_reservation;
    }

    public Centre getCentre() {
        return centre;
    }

    public void setCentre(Centre centre) {
        this.centre = centre;
    }

}

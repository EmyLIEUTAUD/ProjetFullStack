package org.polytech.covid;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_reservation;
    private LocalDate date_reservation;

    @OneToOne
    @JoinColumn(name = "id_personne", referencedColumnName = "id_personne")
    private Public id_personne;

    @OneToOne
    @JoinColumn(name = "id_centre", referencedColumnName = "id_centre")
    private Centre id_centre;

    public Reservation(Integer id_reservation, LocalDate date_reservation, Public id_personne, Centre id_centre) {
        this.id_reservation = id_reservation;
        this.date_reservation = date_reservation;
        this.id_personne = id_personne;
        this.id_centre = id_centre;
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

    public Public getId_personne() {
        return id_personne;
    }

    public void setId_personne(Public id_personne) {
        this.id_personne = id_personne;
    }

    public Centre getId_centre() {
        return id_centre;
    }

    public void setId_centre(Centre id_centre) {
        this.id_centre = id_centre;
    }

}

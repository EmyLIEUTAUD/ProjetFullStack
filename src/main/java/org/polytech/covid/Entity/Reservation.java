package org.polytech.covid.Entity;

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

}

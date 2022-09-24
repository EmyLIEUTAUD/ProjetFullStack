package org.polytech.covid;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_reservation;
    private LocalDate date_reservation;
    private Integer id_personne;
    private Integer id_centre;

}

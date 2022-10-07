package org.polytech.covid.Service;

import org.polytech.covid.Entity.Reservation;

import java.util.List;

public interface MedecinServices {
    List<Reservation> rechercherPersonne(String nom, Integer idCentre);
    void validerVaccination();
}

package org.polytech.covid.Service;

import org.polytech.covid.Entity.Public;
import org.polytech.covid.Entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface MedecinServices {
    List<Reservation> rechercherPersonne(String nom, Integer idCentre);


    Public modifierPublic(Optional<Public> publicData);

}

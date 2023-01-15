package org.polytech.covid.Service;

import org.polytech.covid.Entity.Public;
import org.polytech.covid.Entity.Reservation;

import java.util.List;
import java.util.Optional;

public interface MedecinServices {

    /***
     * Liste des services pour les Medecins
     ***/

    List<Reservation> rechercherPersonne(String nom, Integer idCentre);

    Public modifierPublic(Optional<Public> publicData);

}

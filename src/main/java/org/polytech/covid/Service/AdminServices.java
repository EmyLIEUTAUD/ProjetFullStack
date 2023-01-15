package org.polytech.covid.Service;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Medecin;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Reservation;

public interface AdminServices {

    /***
     * Liste des services pour les Admins
     ***/

    List<Medecin> voirMedecins();

    List<Reservation> voirReservations();

    Medecin creerMedecin(Personne personne, Centre centre);

    Medecin modifierMedecin(Optional<Medecin> medecinData, Medecin medecin);

}

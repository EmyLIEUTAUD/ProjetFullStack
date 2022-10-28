package org.polytech.covid.Service;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Admin;
import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Medecin;
import org.polytech.covid.Entity.Personne;

public interface AdminServices {

    List<Medecin> voirMedecins();

    Medecin creerMedecin(Personne personne, Centre centre);

    Medecin modifierMedecin(Optional<Medecin> medecinData, Medecin medecin);

}

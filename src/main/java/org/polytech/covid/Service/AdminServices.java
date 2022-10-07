package org.polytech.covid.Service;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Medecin;

public interface AdminServices {

    List<Medecin> voirMedecins();

    Medecin creerMedecin(Medecin medecin);

    Medecin modifierMedecin(Optional<Medecin> medecinData, Medecin medecin);

}

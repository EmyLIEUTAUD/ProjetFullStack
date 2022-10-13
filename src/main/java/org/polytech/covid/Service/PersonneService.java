package org.polytech.covid.Service;

import java.util.Optional;

import org.polytech.covid.Entity.Personne;

public interface PersonneService {

    Personne creerPublic(Personne personne);

    Personne creerProfessionnel(Personne personne);

    Personne modifierPublic(Optional<Personne> personneData, Personne personne);

    Personne modifierProfessionnel(Optional<Personne> personneData, Personne personne);

}

package org.polytech.covid.Service;

import java.util.Optional;

import org.polytech.covid.Entity.Personne;

public interface PersonneService {

    Personne creerPersonne(Personne personne);

    Personne modifierPersonne(Optional<Personne> personneData, Personne personne);

}

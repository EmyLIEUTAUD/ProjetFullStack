package org.polytech.covid.Service;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Personne;

public interface PersonneService {

    /***
     * Liste des services pour les Personnes
     ***/

    Personne creerPublic(Personne personne);

    Personne creerProfessionnel(Personne personne);

    Personne modifierPublic(Optional<Personne> personneData, Personne personne);

    Personne modifierProfessionnel(Optional<Personne> personneData, Personne personne);

}

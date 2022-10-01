package org.polytech.covid.Service;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Centre;

public interface CentreServices {

    Centre creerCentre(Centre centre);

    Centre modifierCentre(Optional<Centre> centreData, Centre centre);

    List<Centre> rechercheCentreByVille(String comnom);

    List<Centre> voirCentres();

}

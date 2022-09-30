package org.polytech.covid.Service;

import java.util.Optional;

import org.polytech.covid.Entity.Centre;

public interface ModificerCentreService {

    Centre modifierCentre(Optional<Centre> centreData, Centre centre);

}

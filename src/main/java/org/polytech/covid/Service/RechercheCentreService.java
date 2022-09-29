package org.polytech.covid.Service;

import org.polytech.covid.Entity.Centre;

import java.util.List;

public interface RechercheCentreService {
    List<Centre> rechercheCentreByVille(String comnom);
}

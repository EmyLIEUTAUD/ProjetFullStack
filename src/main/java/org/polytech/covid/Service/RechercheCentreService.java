package org.polytech.covid.Service;

import org.polytech.covid.Dao.CentreDao;
import org.polytech.covid.Entity.Centre;

import java.util.List;

public interface RechercheCentreService {
    List<Centre> rechercheCentreByVille (Integer villeId);
}

package org.polytech.covid.Service;

import org.polytech.covid.Dao.CentreDao;

import java.util.List;

public interface RechercheCentreService {
    List<CentreDao> rechercheCentreByVille (Integer villeId);
}

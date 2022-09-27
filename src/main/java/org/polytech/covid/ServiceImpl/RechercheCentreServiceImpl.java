package org.polytech.covid.ServiceImpl;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Repository.CentreRepositry;
import org.polytech.covid.Service.RechercheCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RechercheCentreServiceImpl implements RechercheCentreService {

    @Autowired
    private CentreRepositry centreRepositry;

    public List<Centre> rechercheCentreByVille(String ville) {
        List<Centre> listCentres = new ArrayList<>();
        listCentres = centreRepositry.findByVille(ville);
        return listCentres;
    }
}

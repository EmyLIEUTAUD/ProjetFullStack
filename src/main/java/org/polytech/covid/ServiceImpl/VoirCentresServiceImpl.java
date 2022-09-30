package org.polytech.covid.ServiceImpl;

import java.util.List;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Repository.CentreRepositry;
import org.polytech.covid.Service.VoirCentresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoirCentresServiceImpl implements VoirCentresService {

    @Autowired
    private CentreRepositry centreRepositry;

    public List<Centre> voirCentres() {
        List<Centre> listCentres = centreRepositry.findAll();
        return listCentres;
    }

}

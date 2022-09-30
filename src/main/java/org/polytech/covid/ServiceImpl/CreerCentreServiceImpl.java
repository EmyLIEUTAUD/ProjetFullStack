package org.polytech.covid.ServiceImpl;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Repository.CentreRepositry;
import org.polytech.covid.Service.CreerCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CreerCentreServiceImpl implements CreerCentreService {

    @Autowired
    private CentreRepositry centreRepository;

    public Centre creerCentre(Centre centre) {
        Centre _centre = centreRepository
                .save(new Centre(centre.getNom(), centre.getComnom(), centre.getNumAdresse(), centre.getAdresse(),
                        centre.getCp(),
                        centre.getHorairesLundi(), centre.getHorairesMardi(), centre.getHorairesMercredi(),
                        centre.getHorairesJeudi(), centre.getHorairesVendredi(), centre.getHorairesSamedi(),
                        centre.getHorairesDimanche()));
        return _centre;
    }

}

package org.polytech.covid.ServiceImpl;

import java.util.Optional;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Repository.CentreRepositry;
import org.polytech.covid.Service.ModificerCentreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModifierCentreServiceImpl implements ModificerCentreService {

    public Centre modifierCentre(Optional<Centre> centreData, Centre centre) {
        Centre _centre = centreData.get();
        _centre.setNom(centre.getNom());
        _centre.setComnom(centre.getComnom());
        _centre.setNumAdresse(centre.getNumAdresse());
        _centre.setAdresse(centre.getAdresse());
        _centre.setCp(centre.getCp());
        _centre.setHorairesLundi(centre.getHorairesLundi());
        _centre.setHorairesMardi(centre.getHorairesMardi());
        _centre.setHorairesMercredi(centre.getHorairesMercredi());
        _centre.setHorairesJeudi(centre.getHorairesJeudi());
        _centre.setHorairesVendredi(centre.getHorairesVendredi());
        _centre.setHorairesSamedi(centre.getHorairesSamedi());
        _centre.setHorairesDimanche(centre.getHorairesDimanche());
        return _centre;
    }

}

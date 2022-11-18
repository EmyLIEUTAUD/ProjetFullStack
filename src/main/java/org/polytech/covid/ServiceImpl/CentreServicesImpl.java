package org.polytech.covid.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Repository.CentreRepository;
import org.polytech.covid.Service.CentreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CentreServicesImpl implements CentreServices {

    @Autowired
    private CentreRepository centreRepository;

    public Centre creerCentre(Centre centre) {
        Centre _centre = centreRepository
                .save(new Centre(centre.getNom(), centre.getComnom(), centre.getNumAdresse(), centre.getAdresse(),
                        centre.getCp(),
                        centre.getHorairesLundi(), centre.getHorairesMardi(), centre.getHorairesMercredi(),
                        centre.getHorairesJeudi(), centre.getHorairesVendredi(), centre.getHorairesSamedi(),
                        centre.getHorairesDimanche()));
        return _centre;
    }

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

    public List<Centre> rechercheCentreByVille(String comnom) {
        String comNom = comnom.substring(0, 1).toUpperCase() + comnom.substring(1).toLowerCase();
        List<Centre> listCentres = centreRepository.findByComnom(comNom);
        return listCentres;
    }

    public List<Centre> voirCentres() {
        List<Centre> listCentres = centreRepository.findAll();
        return listCentres;
    }

}

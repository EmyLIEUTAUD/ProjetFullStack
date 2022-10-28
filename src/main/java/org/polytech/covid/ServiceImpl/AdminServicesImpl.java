package org.polytech.covid.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Admin;
import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Medecin;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Repository.AdminRepository;
import org.polytech.covid.Repository.CentreRepository;
import org.polytech.covid.Repository.MedecinRepository;
import org.polytech.covid.Service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServicesImpl implements AdminServices {

    @Autowired
    private MedecinRepository medecinRepository;

    public List<Medecin> voirMedecins() {
        List<Medecin> listMedecins = medecinRepository.findAll();
        return listMedecins;
    }

    public Medecin creerMedecin(Personne personne, Centre centre) {
        Medecin _medecin = medecinRepository
                .save(new Medecin(personne, centre));
        return _medecin;
    }

    public Medecin modifierMedecin(Optional<Medecin> medecinData, Medecin medecin) {
        Medecin _medecin = medecinData.get();
        _medecin.setCentre(medecin.getCentre());
        _medecin.setPersonne(medecin.getPersonne());
        return _medecin;
    }

    @Autowired
    private CentreRepository centreRepository;

    @Autowired
    private AdminRepository adminRepository;

}

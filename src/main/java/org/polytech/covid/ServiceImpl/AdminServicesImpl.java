package org.polytech.covid.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Admin;
import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Medecin;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Repository.AdminRepository;
import org.polytech.covid.Repository.CentreRepository;
import org.polytech.covid.Repository.MedecinRepository;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServicesImpl implements AdminServices {

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private PersonneRepository personneRepository;

    public List<Medecin> voirMedecins() {
        List<Medecin> listMedecins = medecinRepository.findAll();
        return listMedecins;
    }

    public Medecin creerMedecin(Personne personne, Centre centre) {
        System.out.println("Je veux créer un médecin");
        Medecin _medecin = medecinRepository
                .save(new Medecin(personne, centre));
        Optional<Personne> personneData = personneRepository.findById(personne.getIdentifiant());
        Personne personneP = personneData.get();
        List<String> roles = new ArrayList<String>();
        roles.add("MEDECIN");
        personneP.setRoles(roles);
        personneRepository.save(personneP);
        System.out.println("Médecin créé !");
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

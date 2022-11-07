package org.polytech.covid.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Admin;
import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Medecin;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Reservation;
import org.polytech.covid.Repository.AdminRepository;
import org.polytech.covid.Repository.CentreRepository;
import org.polytech.covid.Repository.MedecinRepository;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Repository.ReservationRepository;
import org.polytech.covid.Service.AdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AdminServicesImpl implements AdminServices {

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Medecin> voirMedecins() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Personne personne = personneRepository.findByMail(authentication.getName()).get();
        Admin admin = adminRepository.findByIdentifiant(personne.getIdentifiant()).get();
        Centre centre = centreRepository.findById(admin.getCentre().getGid()).get();
        List<Medecin> listMedecins = medecinRepository.findByGid(centre.getGid());
        return listMedecins;
    }

    public List<Reservation> voirReservations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Personne personne = personneRepository.findByMail(authentication.getName()).get();
        Admin admin = adminRepository.findByIdentifiant(personne.getIdentifiant()).get();
        Centre centre = centreRepository.findById(admin.getCentre().getGid()).get();
        List<Reservation> listReservations = reservationRepository.findByGid(centre.getGid());
        return listReservations;
    }

    public Medecin creerMedecin(Personne personne, Centre centre) {
        Medecin _medecin = medecinRepository
                .save(new Medecin(personne, centre));
        Optional<Personne> personneData = personneRepository.findById(personne.getIdentifiant());
        Personne personneP = personneData.get();
        List<String> roles = new ArrayList<String>();
        roles.add("MEDECIN");
        personneP.setRoles(roles);
        personneRepository.save(personneP);
        return _medecin;
    }

    public Medecin modifierMedecin(Optional<Medecin> medecinData, Medecin medecin) {
        Medecin _medecin = medecinData.get();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Personne personne = personneRepository.findByMail(authentication.getName()).get();
        Admin admin = adminRepository.findByIdentifiant(personne.getIdentifiant()).get();
        Centre centre = centreRepository.findById(admin.getCentre().getGid()).get();
        _medecin.setCentre(centre);
        _medecin.setPersonne(medecin.getPersonne());
        return _medecin;
    }

    @Autowired
    private CentreRepository centreRepository;

    @Autowired
    private AdminRepository adminRepository;

}

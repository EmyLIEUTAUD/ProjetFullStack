package org.polytech.covid.ServiceImpl;

import io.swagger.models.auth.In;
import liquibase.pro.packaged.A;
import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Reservation;
import org.polytech.covid.Repository.CentreRepository;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Repository.ReservationRepository;
import org.polytech.covid.Service.MedecinServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MedecinServiceImpl implements MedecinServices {

    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CentreRepository centreRepository;
    @Override
    public List<Reservation> rechercherPersonne(String nom, Integer idCentre) {
        Optional<Centre> centreOptional = centreRepository.findById(idCentre);
        Centre centre = centreOptional.get();
        List<Personne> personne = personneRepository.findByNom(nom);
        List<Reservation> reservationList = reservationRepository.findByPersonneAndCentre(personne,centre);
        return reservationList;
    }

    @Override
    public void validerVaccination() {

    }
}

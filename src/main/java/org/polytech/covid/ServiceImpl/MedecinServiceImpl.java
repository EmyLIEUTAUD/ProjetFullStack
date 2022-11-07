package org.polytech.covid.ServiceImpl;

import io.swagger.models.auth.In;
import liquibase.pro.packaged.A;
import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Public;
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
    public List<Reservation> rechercherPersonne(String nom, Integer gid) {
        // List<Reservation> reservationList = reservationRepository.findByGid(gid);
        // List<Personne> personneList = personneRepository.findByNom(nom);
        // List<Reservation> reservationList =
        // reservationRepository.findByPersonneInAndCentre(personneList, gid);
        List<Reservation> reservationList = reservationRepository.findByPersonneAndCentre(nom, gid);
        return reservationList;
    }

    @Override
    public void validerVaccination() {

    }

    @Override
    public Public modifierPublic(Optional<Public> publicData, Public personnePublic) {
        Public _public = publicData.get();
        _public.setDose(personnePublic.getDose() + 1);
        _public.setPersonne(personnePublic.getPersonne());
        return _public;
    }

}

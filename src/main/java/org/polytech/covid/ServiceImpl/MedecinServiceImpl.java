package org.polytech.covid.ServiceImpl;

import org.polytech.covid.Entity.Public;
import org.polytech.covid.Entity.Reservation;
import org.polytech.covid.Repository.ReservationRepository;
import org.polytech.covid.Service.MedecinServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedecinServiceImpl implements MedecinServices {

    /***
     * Description des services pour les Medecins
     ***/

    @Autowired
    private ReservationRepository reservationRepository;

    @Override
    public List<Reservation> rechercherPersonne(String nom, Integer gid) {
        String name = nom.substring(0, 1).toUpperCase() + nom.substring(1).toLowerCase();
        List<Reservation> reservationList = reservationRepository.findByPersonneAndCentre(name, gid);
        return reservationList;
    }

    @Override
    public Public modifierPublic(Optional<Public> publicData) {
        Public _public = publicData.get();
        _public.setDose(_public.getDose() + 1);
        _public.setPersonne(_public.getPersonne());
        return _public;
    }

}
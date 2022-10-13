package org.polytech.covid.ServiceImpl;

import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Reservation;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Repository.ReservationRepository;
import org.polytech.covid.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private PersonneRepository personneRepository;
    @Override
    public Reservation save(Reservation reservation) {
        Personne personne =personneRepository.findPersonneByTelephone(reservation.getPersonne().getTelephone());
        if (personne==null){
            personne = reservation.getPersonne();
        }
        reservation = reservationRepository.save(reservation);
        return reservation;
    }
}

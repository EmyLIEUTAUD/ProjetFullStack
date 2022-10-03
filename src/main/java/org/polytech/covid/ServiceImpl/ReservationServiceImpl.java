package org.polytech.covid.ServiceImpl;

import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Reservation;
import org.polytech.covid.Repository.ReservationRepository;
import org.polytech.covid.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;
    @Override
    public Reservation save(Reservation reservation) {
        Personne personne = reservation.getPersonne();
        personne.setReservation(reservation);
        reservation = reservationRepository.save(reservation);
        return reservation;
    }
}

package org.polytech.covid.ServiceImpl;

import java.util.Optional;

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
        Optional<Personne> personne = personneRepository.findByMail((reservation.getPersonne().getMail()));
        if (personne == null) {
            // personne = reservation.getPersonne();
            personneRepository.save(reservation.getPersonne());
        }
        reservation = reservationRepository.save(reservation);
        return reservation;
    }
}

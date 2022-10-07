package org.polytech.covid.Controller;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Reservation;
import org.polytech.covid.Service.CentreServices;
import org.polytech.covid.Service.PersonneService;
import org.polytech.covid.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private CentreServices centreServices;
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/centres")
    public List<Centre> voirCentres() {
        return centreServices.voirCentres();
    }

    @GetMapping(path = "/centres/{com_nom}")
    public List<Centre> rechercheCentreByVille(@PathVariable(value = "com_nom") String com_nom) {
        return centreServices.rechercheCentreByVille(com_nom);
    }

    @PostMapping(path = "/inscription")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservation,
            UriComponentsBuilder uriBuilder) {
        Reservation saveReservation = reservationService.save(reservation);
        URI uri = uriBuilder.path("/reservation/{id}").buildAndExpand(reservation.getId_reservation()).toUri();
        return ResponseEntity.created(uri).body(saveReservation);
    }

    @Autowired
    private PersonneService personneService;

    @PostMapping("/nouvellePersonne")
    public ResponseEntity<Personne> createPersonne(@RequestBody Personne personne) {
        try {
            Personne _personne;
            _personne = personneService.creerPersonne(personne);
            return new ResponseEntity<>(_personne, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

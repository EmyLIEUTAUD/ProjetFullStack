package org.polytech.covid.Controller;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Reservation;
import org.polytech.covid.Service.CentreServices;
import org.polytech.covid.Service.MedecinServices;
import org.polytech.covid.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private MedecinServices medecinServices;

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

    @GetMapping(path = "/medecin/planning/{nom}/{gid}")
    public List<Reservation> rechercherPersonne(@PathVariable(value = "nom")String nom,@PathVariable(value = "gid")Integer gid){
        return medecinServices.rechercherPersonne(nom,gid);
    }
}

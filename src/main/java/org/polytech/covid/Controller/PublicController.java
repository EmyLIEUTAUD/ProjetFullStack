package org.polytech.covid.Controller;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Reservation;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Repository.ReservationRepository;
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
import java.util.Optional;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private CentreServices centreServices;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private ReservationRepository reservationRepository;

    @GetMapping("/centres")
    public List<Centre> voirCentres() {
        return centreServices.voirCentres();
    }

    @GetMapping(path = "/centres/{com_nom}")
    public List<Centre> rechercheCentreByVille(@PathVariable(value = "com_nom") String com_nom) {
        return centreServices.rechercheCentreByVille(com_nom);
    }

    @PostMapping(path = "/inscription")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservationRequest,
            UriComponentsBuilder uriBuilder) {
        Optional<Personne> personneRequest = personneRepository.findByMail(reservationRequest.getPersonne().getMail());
        Personne personneSave=personneRequest.get();
        if (personneRequest==null){
            personneSave.setNom(reservationRequest.getPersonne().getNom());
            personneSave.setAdresse(reservationRequest.getPersonne().getAdresse());
            personneSave.setMail(reservationRequest.getPersonne().getMail());
            personneSave.setPrenom(reservationRequest.getPersonne().getPrenom());
            personneSave.setMdp(reservationRequest.getPersonne().getMdp());
            personneSave.setTelephone(reservationRequest.getPersonne().getTelephone());
            //reservationRequest.setPersonne(personneSave);
            //reservationRepository.save(reservationRequest);
        }
            Reservation reservation = personneSave.map((Personne personne) -> {
                reservationRequest.setPersonne(personne);
                return reservationRepository.save(reservationRequest);
            });


        URI uri = uriBuilder.path("/reservation/{id}").buildAndExpand(reservation.getId_reservation()).toUri();
        return ResponseEntity.created(uri).body(reservation);
    }

    @Autowired
    private PersonneService personneService;

    @PostMapping("/personne/nouvelle")
    public ResponseEntity<Personne> createPersonne(@RequestBody Personne personne) {
        try {
            Personne _personne;
            _personne = personneService.creerPersonne(personne);
            return new ResponseEntity<>(_personne, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/personne/modifier/{id}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable("id") Integer id, @RequestBody Personne personne) {
        Optional<Personne> personneData = personneRepository.findById(id);

        if (personneData.isPresent()) {
            Personne _personne;
            _personne = personneService.modifierPersonne(personneData, personne);
            return new ResponseEntity<>(personneRepository.save(_personne), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/personne/supprimer/{id}")
    public ResponseEntity<HttpStatus> deleteMedecin(@PathVariable("id") Integer id) {
        try {
            personneRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

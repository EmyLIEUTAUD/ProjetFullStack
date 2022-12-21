package org.polytech.covid.Controller;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Reservation;
import org.polytech.covid.Repository.CentreRepository;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Repository.ReservationRepository;
import org.polytech.covid.Service.CentreServices;
import org.polytech.covid.Service.PersonneService;
import org.polytech.covid.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import io.github.bucket4j.*;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;

import java.net.URI;
import java.time.Duration;
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
    @Autowired
    private CentreRepository centreRepository;

    @GetMapping("/centres")
    public List<Centre> voirCentres() {
        return centreServices.voirCentres();
    }

    @GetMapping("/centres/id/{gid}")
    public ResponseEntity<Centre> rechercheCentreByGid(@PathVariable(value = "gid") Integer gid) {
        Optional<Centre> centreData = centreRepository.findById(gid);
        if (centreData.isPresent()) {
            return new ResponseEntity<>(centreData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/centres/{com_nom}")
    public List<Centre> rechercheCentreByVille(@PathVariable(value = "com_nom") String com_nom) {
        return centreServices.rechercheCentreByVille(com_nom);
    }

    // rajoute 10 tokens toutes les minutes
    Refill refill = Refill.intervally(10, Duration.ofMinutes(1));
    // capacité max de 10 token
    Bandwidth limit = Bandwidth.classic(10, refill);
    Bucket bucket = Bucket.builder().addLimit(limit).build();

    @PostMapping(path = "/inscription")
    @Timed(value = "rendez-vous.temps.enregistrement", description = "Temps d'enregistrement d'un rendez-vous")
    @Counted(value = "rendez-vous.nombre", description = "Nombre de rendez-vous pris")
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservationRequest) {
        if (bucket.tryConsume(1)) {
            Optional<Personne> personneRequest = personneRepository
                    .findByMail(reservationRequest.getPersonne().getMail());

            if (!personneRequest.isPresent()) {
                Personne personneSave = new Personne();
                personneSave.setNom(reservationRequest.getPersonne().getNom());
                personneSave.setMail(reservationRequest.getPersonne().getMail());
                personneSave.setPrenom(reservationRequest.getPersonne().getPrenom());
                reservationRequest.setPersonne(personneSave);
                reservationRepository.save(reservationRequest);
                return new ResponseEntity<>(reservationRequest, HttpStatus.CREATED);
            } else {
                Optional<Reservation> reservation = personneRequest.map((Personne personne) -> {
                    reservationRequest.setPersonne(personne);

                    return reservationRepository.save(reservationRequest);
                });

                return new ResponseEntity<>(reservation.get(), HttpStatus.CREATED);
            }
        }
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    @GetMapping(value = "/inscription/infos")
    public ResponseEntity<String> infos() {

        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            return ResponseEntity.ok()
                    .header("X-Rate-Limit-Remaining", Long.toString(probe.getRemainingTokens()))
                    .body("infos");
        }
        long delaiEnSeconde = probe.getNanosToWaitForRefill() / 1_000_000_000;
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                .header("X-Rate-Limit-Retry-After-Seconds", String.valueOf(delaiEnSeconde))
                .build();
    }

    @Autowired
    private PersonneService personneService;

    @PutMapping("/personne/modifier/{id}")
    public ResponseEntity<Personne> updatePersonne(@PathVariable("id") Integer id, @RequestBody Personne personne) {
        Optional<Personne> personneData = personneRepository.findById(id);

        if (personneData.isPresent()) {
            Personne _personne;
            _personne = personneService.modifierPublic(personneData, personne);
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

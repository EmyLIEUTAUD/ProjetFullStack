package org.polytech.covid.Controller;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Public;
import org.polytech.covid.Entity.Reservation;
import org.polytech.covid.Repository.CentreRepository;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Repository.PublicRepository;
import org.polytech.covid.Repository.ReservationRepository;
import org.polytech.covid.Service.CentreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.bucket4j.*;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/public")
@CrossOrigin(origins = "http://localhost:4200")
public class PublicController {

    /***
     * Endpoint public destiné aux patients pour prendre rendez-vous dans un centre
     * pour une vaccination
     ***/

    @Autowired
    private CentreServices centreServices;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private CentreRepository centreRepository;
    @Autowired
    private PublicRepository publicRepository;

    @GetMapping("/centres")
    public ResponseEntity<List<Centre>> voirCentres() {
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .body(centreServices.voirCentres());
    }

    @GetMapping("/centres/id/{gid}")
    public ResponseEntity<Centre> rechercheCentreByGid(@PathVariable(value = "gid") Integer gid) {
        Optional<Centre> centreData = centreRepository.findById(gid);
        if (centreData.isPresent()) {
            return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS)).body(centreData.get());
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/centres/{com_nom}")
    public ResponseEntity<List<Centre>> rechercheCentreByVille(@PathVariable(value = "com_nom") String com_nom) {
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .body(centreServices.rechercheCentreByVille(com_nom));
    }

    // rajoute 10 tokens toutes les minutes
    Refill refill = Refill.intervally(10, Duration.ofMinutes(1));
    // capacité max de 10 token
    Bandwidth limit = Bandwidth.classic(10, refill);
    Bucket bucket = Bucket.builder().addLimit(limit).build();

    final String remaining = "X-Rate-Limit-Remaining";
    final String retryAfter = "X-Rate-Limit-Retry-After-Seconds";

    @PostMapping(path = "/inscription")
    @Timed(value = "rendez-vous.temps.enregistrement", description = "Temps d'enregistrement d'un rendez-vous")
    @Counted(value = "rendez-vous.nombre", description = "Nombre de rendez-vous pris")
    @CrossOrigin(exposedHeaders = { remaining, retryAfter })
    public ResponseEntity<Reservation> saveReservation(@RequestBody Reservation reservationRequest) {
        HttpHeaders headers = new HttpHeaders();
        ConsumptionProbe probe = bucket.tryConsumeAndReturnRemaining(1);
        if (probe.isConsumed()) {
            headers.add("X-Rate-Limit-Remaining", Long.toString(probe.getRemainingTokens()));
            Optional<Personne> personneRequest = personneRepository
                    .findByMail(reservationRequest.getPersonne().getMail());

            if (!personneRequest.isPresent()) {
                Personne personneSave = new Personne();
                personneSave.setNom(reservationRequest.getPersonne().getNom());
                personneSave.setMail(reservationRequest.getPersonne().getMail());
                personneSave.setPrenom(reservationRequest.getPersonne().getPrenom());
                personneRepository.save(personneSave);
                reservationRequest.setPersonne(personneSave);
                reservationRepository.save(reservationRequest);
                Public publicSave = new Public(personneSave, 0);
                publicRepository.save(publicSave);
                return ResponseEntity.status(HttpStatus.CREATED).headers(headers)
                        .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                        .body(reservationRequest);
            } else {
                Optional<Reservation> reservation = personneRequest.map((Personne personne) -> {
                    reservationRequest.setPersonne(personne);

                    return reservationRepository.save(reservationRequest);
                });
                return ResponseEntity.status(HttpStatus.CREATED).headers(headers)
                        .cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS))
                        .body(reservation.get());
            }
        }
        long delaiEnSeconde = probe.getNanosToWaitForRefill() / 1_000_000_000;
        headers.add("X-Rate-Limit-Retry-After-Seconds", String.valueOf(delaiEnSeconde));
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).headers(headers).build();
    }

}
package org.polytech.covid.Controller;

import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/login")
public class LoginController {

    @GetMapping()
    public ResponseEntity<Void> login() {
        return ResponseEntity.ok().build();
    }

    @Autowired
    private PersonneService personneService;

    @PostMapping("/nouveau")
    public ResponseEntity<Personne> createPersonne(@RequestBody Personne personne) {
        try {
            Personne _personne;
            _personne = personneService.creerProfessionnel(personne);
            return new ResponseEntity<>(_personne, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

package org.polytech.covid.Controller;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Service.CentreServices;
import org.polytech.covid.Service.PersonneService;
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
    private PersonneService personneService;

    @GetMapping(path = "/centres/{com_nom}")
    public List<Centre> rechercheCentreByVille(@PathVariable(value = "com_nom") String com_nom) {
        return centreServices.rechercheCentreByVille(com_nom);
    }
    @PostMapping(path = "/inscription")
    public ResponseEntity<Personne> savePersonne(@RequestBody Personne personne, UriComponentsBuilder uriBuilder){
        Personne savePersonne = personneService.save(personne);
        URI uri = uriBuilder.path("/patient/{id}").buildAndExpand(personne.getIdentifiant()).toUri();
        return ResponseEntity.created(uri).body(savePersonne);
    }
}

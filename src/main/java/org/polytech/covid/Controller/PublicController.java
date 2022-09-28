package org.polytech.covid.Controller;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Repository.CentreRepositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    CentreRepositry centreRepositry;

    @GetMapping(path = "/centres/{ville}")
    public ResponseEntity<List<Centre>> rechercheCentreByVille(@PathVariable(value = "ville") String ville) {
        List<Centre> centres = new ArrayList<>();
        if (ville == null) {
            centreRepositry.findAll().forEach(centres::add);
        } else {
            centreRepositry.findByVille(ville).forEach(centres::add);
        }

        if (centres.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(centres, HttpStatus.NO_CONTENT);
    }

}

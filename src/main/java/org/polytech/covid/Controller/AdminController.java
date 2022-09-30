package org.polytech.covid.Controller;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Helper.CSVHelper;
import org.polytech.covid.Message.ResponseMessage;
import org.polytech.covid.Repository.CentreRepositry;
import org.polytech.covid.Service.CSVService;
import org.polytech.covid.Service.CreerCentreService;
import org.polytech.covid.Service.ModificerCentreService;
import org.polytech.covid.Service.VoirCentresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    CSVService fileService;

    @PostMapping("csv/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileService.save(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                System.out.println(e);
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = "Please upload a csv file!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }

    @Autowired
    private VoirCentresService voirCentresService;

    @Autowired
    CentreRepositry centreRepository;

    @GetMapping("/centres")
    public List<Centre> voirCentres() {
        return voirCentresService.voirCentres();
    }

    @Autowired
    private CreerCentreService creerCentreService;

    /*
     * Données envoyées en JSON (remplir la partie de droite):
     * {
     * "nom":"",
     * "comnom":"",
     * "numAdresse":"",
     * "adresse":"",
     * "cp":"",
     * "horairesLundi":"",
     * "horairesMardi":"",
     * "horairesMercredi":"",
     * "horairesJeudi":"",
     * "horairesVendredi":"",
     * "horairesSamedi":"",
     * "horairesDimanche":""
     * }
     */
    @PostMapping("/centres/nouveau")
    public ResponseEntity<Centre> createCenter(@RequestBody Centre centre) {
        try {
            Centre _centre;
            _centre = creerCentreService.creerCentre(centre);
            return new ResponseEntity<>(_centre, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private ModificerCentreService modificerCentreService;

    @PutMapping("/centres/modifier/{gid}")
    public ResponseEntity<Centre> updateCenter(@PathVariable("gid") Integer gid, @RequestBody Centre centre) {
        Optional<Centre> centreData = centreRepository.findById(gid);

        if (centreData.isPresent()) {
            Centre _centre;
            _centre = modificerCentreService.modifierCentre(centreData, centre);
            return new ResponseEntity<>(centreRepository.save(_centre), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/centres/supprimer/{gid}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("gid") Integer gid) {
        try {
            centreRepository.deleteById(gid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

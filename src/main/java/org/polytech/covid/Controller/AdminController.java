package org.polytech.covid.Controller;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Admin;
import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Entity.Medecin;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Reservation;
import org.polytech.covid.Helper.CSVHelper;
import org.polytech.covid.Message.ResponseMessage;
import org.polytech.covid.Repository.AdminRepository;
import org.polytech.covid.Repository.CentreRepository;
import org.polytech.covid.Repository.MedecinRepository;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Service.AdminServices;
import org.polytech.covid.Service.CSVService;
import org.polytech.covid.Service.CentreServices;
import org.polytech.covid.Service.MedecinServices;
import org.polytech.covid.Service.PersonneService;
import org.polytech.covid.Service.SuperAdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    CentreRepository centreRepository;

    @Autowired
    CentreServices centreServices;

    @GetMapping("/centres")
    public List<Centre> voirCentres() {
        return centreServices.voirCentres();
    }

    @PostMapping("/centres/nouveau")
    public ResponseEntity<Centre> createCenter(@RequestBody Centre centre) {
        try {
            Centre _centre;
            _centre = centreServices.creerCentre(centre);
            return new ResponseEntity<>(_centre, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/centres/modifier/{gid}")
    public ResponseEntity<Centre> updateCenter(@PathVariable("gid") Integer gid, @RequestBody Centre centre) {
        Optional<Centre> centreData = centreRepository.findById(gid);

        if (centreData.isPresent()) {
            Centre _centre;
            _centre = centreServices.modifierCentre(centreData, centre);
            return new ResponseEntity<>(centreRepository.save(_centre), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/centres/supprimer/{gid}")
    public ResponseEntity<HttpStatus> deleteCentre(@PathVariable("gid") Integer gid) {
        try {
            centreRepository.deleteById(gid);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private SuperAdminServices superAdminServices;

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/administrateurs")
    public List<Admin> voirAdmins() {
        return superAdminServices.voirAdmins();
    }

    @Autowired
    private PersonneService personneService;

    @PostMapping("/administrateurs/nouveau")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        try {
            Admin _admin;
            _admin = superAdminServices.creerAdmin(admin);
            return new ResponseEntity<>(_admin, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/administrateurs/modifier/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable("id") Integer id, @RequestBody Admin admin) {
        Optional<Admin> adminData = adminRepository.findById(id);

        if (adminData.isPresent()) {
            Admin _admin;
            _admin = superAdminServices.modifierAdmin(adminData, admin);
            return new ResponseEntity<>(adminRepository.save(_admin), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/administrateurs/supprimer/{id}")
    public ResponseEntity<HttpStatus> deleteAdmin(@PathVariable("id") Integer id) {
        try {
            adminRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private AdminServices adminServices;

    @Autowired
    private MedecinRepository medecinRepository;

    @Autowired
    private PersonneRepository personneRepository;

    @GetMapping("/medecins")
    public List<Medecin> voirMedecins() {
        return adminServices.voirMedecins();
    }

    @PostMapping("/medecins/nouveau")
    public ResponseEntity<Medecin> createMedecin(@RequestBody Medecin medecin) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Personne personne = personneRepository.findByMail(authentication.getName()).get();
            Admin admin = adminRepository.findByIdentifiant(personne.getIdentifiant()).get();
            Centre centre = centreRepository.findById(admin.getCentre().getGid()).get();
            Medecin _medecin;
            _medecin = adminServices.creerMedecin(medecin.getPersonne(), centre);
            return new ResponseEntity<>(_medecin, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/medecins/modifier/{id}")
    public ResponseEntity<Medecin> updateMedecin(@PathVariable("id") Integer id, @RequestBody Medecin medecin) {
        Optional<Medecin> medecinData = medecinRepository.findById(id);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Personne personne = personneRepository.findByMail(authentication.getName()).get();
        Admin admin = adminRepository.findByIdentifiant(personne.getIdentifiant()).get();
        Centre centre = centreRepository.findById(admin.getCentre().getGid()).get();
        if (medecinData.isPresent()) {
            if (medecinData.get().getCentre().getGid() == centre.getGid()) {
                Medecin _medecin;
                _medecin = adminServices.modifierMedecin(medecinData, medecin);
                return new ResponseEntity<>(medecinRepository.save(_medecin), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/medecins/supprimer/{id}")
    public ResponseEntity<HttpStatus> deleteMedecin(@PathVariable("id") Integer id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Personne personne = personneRepository.findByMail(authentication.getName()).get();
            Admin admin = adminRepository.findByIdentifiant(personne.getIdentifiant()).get();
            Centre centre = centreRepository.findById(admin.getCentre().getGid()).get();
            Medecin medecin = medecinRepository.findById(id).get();
            if (medecin.getCentre().getGid() == centre.getGid()) {
                medecinRepository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<HttpStatus>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Autowired
    private MedecinServices medecinServices;

    @GetMapping(path = "/medecin/planning/{nom}/{gid}")
    public List<Reservation> rechercherPersonne(@PathVariable(value = "nom") String nom,
            @PathVariable(value = "gid") Integer gid) {
        return medecinServices.rechercherPersonne(nom, gid);
    }

}

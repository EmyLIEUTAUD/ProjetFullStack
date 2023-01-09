package org.polytech.covid.Controller;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.polytech.covid.Entity.*;
import org.polytech.covid.Helper.CSVHelper;
import org.polytech.covid.Message.ResponseMessage;
import org.polytech.covid.Repository.AdminRepository;
import org.polytech.covid.Repository.CentreRepository;
import org.polytech.covid.Repository.MedecinRepository;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Repository.PublicRepository;
import org.polytech.covid.Repository.ReservationRepository;
import org.polytech.covid.Service.AdminServices;
import org.polytech.covid.Service.CSVService;
import org.polytech.covid.Service.CentreServices;
import org.polytech.covid.Service.MedecinServices;
import org.polytech.covid.Service.PersonneService;
import org.polytech.covid.Service.SuperAdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<Centre>> voirCentres() {
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .body(centreServices.voirCentres());
    }

    @PostMapping("/centres/nouveau")
    public ResponseEntity<Centre> createCenter(@RequestBody Centre centre) {
        try {
            Centre _centre;
            _centre = centreServices.creerCentre(centre);
            return ResponseEntity.status(HttpStatus.CREATED).cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                    .body(_centre);
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
            return ResponseEntity.ok().cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                    .body(centreRepository.save(_centre));
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
            return ResponseEntity.status(HttpStatus.CREATED).cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                    .body(_admin);
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
            return ResponseEntity.ok().cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                    .body(adminRepository.save(_admin));
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
    public ResponseEntity<List<Medecin>> voirMedecins() {
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .body(adminServices.voirMedecins());
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
            return ResponseEntity.status(HttpStatus.CREATED).cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                    .body(_medecin);
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
                return ResponseEntity.ok().cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                        .body(medecinRepository.save(_medecin));
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

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> voirReservations() {
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .body(adminServices.voirReservations());
    }

    @Autowired
    private ReservationRepository reservationRepository;

    @DeleteMapping("reservations/supprimer/{id}")
    public ResponseEntity<HttpStatus> deleteReservation(@PathVariable("id") Integer id) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Personne personne = personneRepository.findByMail(authentication.getName()).get();
            Admin admin = adminRepository.findByIdentifiant(personne.getIdentifiant()).get();
            Centre centre = centreRepository.findById(admin.getCentre().getGid()).get();
            Reservation reservation = reservationRepository.findById(id).get();
            if (reservation.getCentre().getGid() == centre.getGid()) {
                reservationRepository.deleteById(id);
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

    @GetMapping(path = "/personnes/{nom}")
    public ResponseEntity<List<Reservation>> rechercherPersonne(@PathVariable(value = "nom") String nom) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Personne personne = personneRepository.findByMail(authentication.getName()).get();
        Medecin medecin = medecinRepository.findById(personne.getIdentifiant()).get();
        Centre centre = centreRepository.findById(medecin.getCentre().getGid()).get();
        return ResponseEntity.ok().cacheControl(CacheControl.maxAge(1, TimeUnit.HOURS))
                .body(medecinServices.rechercherPersonne(nom, centre.getGid()));
    }

    @Autowired
    private PublicRepository publicRepository;

    @PutMapping("/personnes/validerVaccination/{id}")
    public ResponseEntity<Public> updateVaccination(@PathVariable("id") Integer id,
            @RequestBody Public personnePublic) {
        Optional<Public> publicData = publicRepository.findById(id);
        // TODO : voir si besoin de vérifier que la personne a bien eu son rendez-vous
        // Authentication authentication =
        // SecurityContextHolder.getContext().getAuthentication();
        // Personne personne =
        // personneRepository.findByMail(authentication.getName()).get();
        // Medecin medecin =
        // medecinRepository.findById(personne.getIdentifiant()).get();
        // Centre centre =
        // centreRepository.findById(medecin.getCentre().getGid()).get();
        if (publicData.isPresent()) {
            Public _public;
            _public = medecinServices.modifierPublic(publicData, personnePublic);
            return ResponseEntity.ok().cacheControl(CacheControl.maxAge(10, TimeUnit.SECONDS))
                    .body(publicRepository.save(_public));
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

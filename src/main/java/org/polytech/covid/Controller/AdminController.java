package org.polytech.covid.Controller;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Admin;
import org.polytech.covid.Entity.Centre;
import org.polytech.covid.Helper.CSVHelper;
import org.polytech.covid.Message.ResponseMessage;
import org.polytech.covid.Repository.AdminRepository;
import org.polytech.covid.Repository.CentreRepositry;
import org.polytech.covid.Service.CSVService;
import org.polytech.covid.Service.CentreServices;
import org.polytech.covid.Service.SuperAdminServices;
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
    CentreRepositry centreRepository;

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

    @PostMapping("/administrateur/nouveau")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        try {
            Admin _admin;
            _admin = superAdminServices.creerAdmin(admin);
            return new ResponseEntity<>(_admin, HttpStatus.CREATED);
        } catch (Exception e) {
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

}

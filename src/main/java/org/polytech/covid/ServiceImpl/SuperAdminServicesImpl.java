package org.polytech.covid.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.*;
import org.polytech.covid.Repository.AdminRepository;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Repository.SuperadminRepository;
import org.polytech.covid.Service.SuperAdminServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SuperAdminServicesImpl implements SuperAdminServices {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private SuperadminRepository superadminRepository;

    public List<Admin> voirAdmins() {
        List<Admin> listAdmins = adminRepository.findAll();
        return listAdmins;
    }

    public List<Admin> voirAdminsByCentre(Integer gid) {
        List<Admin> listAdmins = adminRepository.findByGid(gid);
        return listAdmins;
    }

    public List<Superadmin> voirSuperAdmins() {
        List<Superadmin> listSuperadmin = superadminRepository.findAll();
        return listSuperadmin;
    }

    public Admin creerAdmin(Admin admin) {
        Admin _admin = adminRepository
                .save(new Admin(admin.getPersonne(), admin.getCentre()));
        Optional<Personne> personneData = personneRepository.findById(admin.getPersonne().getIdentifiant());
        Personne personne = personneData.get();
        List<String> roles = new ArrayList<String>();
        roles.add("ADMIN");
        personne.setRoles(roles);
        personneRepository.save(personne);
        return _admin;
    }

    public Admin modifierAdmin(Optional<Admin> adminData, Admin admin) {
        Admin _admin = adminData.get();
        _admin.setCentre(admin.getCentre());
        _admin.setPersonne(admin.getPersonne());
        return _admin;
    }

}

package org.polytech.covid.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Admin;
import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Repository.AdminRepository;
import org.polytech.covid.Repository.PersonneRepository;
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

    public List<Admin> voirAdmins() {
        List<Admin> listAdmins = adminRepository.findAll();
        return listAdmins;
    }

    public Admin creerAdmin(Admin admin) {
        Admin _admin = adminRepository
                .save(new Admin(admin.getPersonne(), admin.getCentre()));
        Optional<Personne> personneData = personneRepository.findById(admin.getPersonne().getIdentifiant());
        Personne personne = personneData.get();
        System.out.println("J'ai récupéré la personne dans createAdmin");
        personne.setRoles(List.of("ADMIN"));
        System.out.println("personne createAdmin " + personne.toString());
        System.out.println("J'ai mis le rôle ADMIN dans createAdmin");
        System.out.println(personneRepository);

        // C'est cette ligne qui bloque : elle ne peut pas enregistrer le changement
        // dans la bdd
        personneRepository.save(personne);

        System.out.println("personne modifiée dans createAdmin");
        return _admin;
    }

    public Admin modifierAdmin(Optional<Admin> adminData, Admin admin) {
        Admin _admin = adminData.get();
        _admin.setCentre(admin.getCentre());
        _admin.setPersonne(admin.getPersonne());
        return _admin;
    }

}

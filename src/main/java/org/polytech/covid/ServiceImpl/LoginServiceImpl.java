package org.polytech.covid.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Superadmin;
import org.polytech.covid.Repository.AdminRepository;
import org.polytech.covid.Repository.MedecinRepository;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Repository.SuperadminRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginServiceImpl {

    private static Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
    private final PersonneRepository personneRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private SuperadminRepository superadminRepository;

    @Autowired
    public LoginServiceImpl(final PersonneRepository personneRepository, PasswordEncoder passwordEncoder) {
        this.personneRepository = personneRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Pour l'exemple j'enregistre un utilisateur au demarrage
     */
    @PostConstruct
    public void createUserDefault() {
        log.info("Creation du user superAdmin");
        Personne superAdmin = new Personne();
        superAdmin.setNom("superAdmin");
        superAdmin.setPrenom("superAdmin");
        superAdmin.setMail("superAdmin@gmail.com");
        superAdmin.setMdp(passwordEncoder.encode("superAdminPassword"));
        superAdmin.setRoles(List.of("SUPER_ADMIN"));
        this.personneRepository.save(superAdmin);
        Superadmin sa = new Superadmin();
        sa.setPersonne(superAdmin);
        superadminRepository.save(sa);
    }



}

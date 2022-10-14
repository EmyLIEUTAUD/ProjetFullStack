package org.polytech.covid.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Repository.PersonneRepository;

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
public class LoginServiceImpl implements UserDetailsService {

    private static Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);
    private final PersonneRepository personneRepository;
    private final PasswordEncoder passwordEncoder;

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
        log.info("Creation du user médecin");
        Personne medecin = new Personne();
        medecin.setNom("medecin");
        medecin.setPrenom("medecin");
        medecin.setMail("medecin@gmail.com");
        medecin.setMdp(passwordEncoder.encode("medecinPassword"));
        medecin.setRoles(List.of("MEDECIN"));
        this.personneRepository.save(medecin);
        log.info("Creation du user admin");
        Personne admin = new Personne();
        admin.setNom("admin");
        admin.setPrenom("admin");
        admin.setMail("admin@gmail.com");
        admin.setMdp(passwordEncoder.encode("adminPassword"));
        admin.setRoles(List.of("ADMIN"));
        this.personneRepository.save(admin);
        log.info("Creation du user superAdmin");
        Personne superAdmin = new Personne();
        superAdmin.setNom("superAdmin");
        superAdmin.setPrenom("superAdmin");
        superAdmin.setMail("superAdmin@gmail.com");
        superAdmin.setMdp(passwordEncoder.encode("superAdminPassword"));
        superAdmin.setRoles(List.of("SUPER_ADMIN"));
        this.personneRepository.save(superAdmin);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(final String mail) throws UsernameNotFoundException {
        log.info("recuperation de {}", mail);

        Optional<Personne> optionalPersonne = personneRepository.findByMail(mail);
        if (optionalPersonne.isPresent()) {
            Personne personne = optionalPersonne.get();
            return new User(personne.getMail(), personne.getMdp(),
                    personne.getRoles().stream().map(SimpleGrantedAuthority::new).toList());
        } else {
            throw new UsernameNotFoundException("L'utilisateur" + mail + " n'existe pas");
        }

    }

}
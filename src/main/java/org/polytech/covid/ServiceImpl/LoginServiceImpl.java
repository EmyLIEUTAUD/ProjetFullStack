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
        log.info("Creation du user par défaut");
        Personne personne = new Personne();
        personne.setNom("test");
        personne.setPrenom("test");
        personne.setMail("test@gmail.com");

        personne.setMdp(passwordEncoder.encode("test"));
        personne.setRoles(List.of("PUBLIC"));
        this.personneRepository.save(personne);
        log.info("Creation du user admin");
        Personne admin = new Personne();
        admin.setNom("admin");
        admin.setPrenom("admin");
        admin.setMail("admin@gmail.com");

        admin.setMdp(passwordEncoder.encode("adminPassword"));
        admin.setRoles(List.of("SUPER_ADMIN"));
        this.personneRepository.save(admin);
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

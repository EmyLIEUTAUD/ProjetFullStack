package org.polytech.covid.ServiceImpl;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Repository.PersonneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
        log.info("Creation du user par defaut");
        Personne personne = new Personne();
        personne.setNom("admin");
        personne.setPrenom("admin");
        personne.setMail("admin@gmail.com");
        personne.setTelephone("");
        personne.setAdresse("");
        personne.setMdp(passwordEncoder.encode("adminPassword"));
        this.personneRepository.save(personne);
    }

    @Override
    public UserDetails loadUserByUsername(final String mail) throws UsernameNotFoundException {
        log.info("recuperation de {}", mail);

        Optional<Personne> optionalPersonne = personneRepository.findByMail(mail);
        if (optionalPersonne.isPresent()) {
            Personne personne = optionalPersonne.get();
            return new User(personne.getMail(), personne.getMdp(), List.of());
        } else {
            throw new UsernameNotFoundException("L'utilisateur" + mail + " n'existe pas");
        }

    }

}

package org.polytech.covid.Service;

import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Superadmin;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Repository.SuperadminRepository;
import org.polytech.covid.ServiceImpl.LoginServiceImpl;
import org.polytech.covid.model.UserDTO;
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

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonneRepository personneRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Personne> personne = personneRepository.findByMail(username);
        if(!personne.isPresent()){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Personne user = personne.get();
        return new org.springframework.security.core.userdetails.User(user.getMail(), user.getMdp(),getAuthority(user));

    }
    private Set<SimpleGrantedAuthority> getAuthority(Personne user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });
        return authorities;
    }
    public Personne save(UserDTO user) {
        Personne newUser = new Personne();
        newUser.setMail(user.getUsername());
        newUser.setMdp(bcryptEncoder.encode(user.getPassword()));
        newUser.setNom(user.getNom());
        newUser.setPrenom(user.getPrenom());
        return personneRepository.save(newUser);
    }




}
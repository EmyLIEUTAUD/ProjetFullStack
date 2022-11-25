package org.polytech.covid.Service;

import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Repository.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonneRepository personneRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Personne> personne = personneRepository.findByMail(username);
        if(!personne.isPresent()){
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Personne user = personne.get();
        return new org.springframework.security.core.userdetails.User(user.getMail(), user.getMdp(),
                new ArrayList<>());
    }
}
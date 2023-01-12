package org.polytech.covid.ServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Entity.Public;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Repository.PublicRepository;
import org.polytech.covid.Service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class PersonneServiceImpl implements PersonneService {

    @Autowired
    private PersonneRepository personneRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private PublicRepository publicRepository;

    @Autowired
    public PersonneServiceImpl(final PersonneRepository personneRepository, PasswordEncoder passwordEncoder) {
        this.personneRepository = personneRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Personne creerPublic(Personne personne) {
        // TODO : si la personne n'existe pas : faire ça
        Personne _personne = personneRepository
                .save(new Personne(personne.getNom(), personne.getPrenom(), personne.getMail()));
        Public _public = publicRepository.save(new Public(_personne, 0));
        // sinon : pré-remplir le formulaire avec les infos de la personne
        return _personne;
    }

    public Personne creerProfessionnel(Personne personne) {
        Personne _personne = personneRepository
                .save(new Personne(personne.getNom(), personne.getPrenom(), personne.getMail(),
                        passwordEncoder.encode(personne.getMdp()),
                        new ArrayList<>()));
        return _personne;
    }

    public Personne modifierPublic(Optional<Personne> personneData, Personne personne) {
        Personne _personne = personneData.get();
        _personne.setNom(personne.getNom());
        _personne.setPrenom(personne.getPrenom());
        _personne.setMail(personne.getMail());
        return _personne;
    }

    public Personne modifierProfessionnel(Optional<Personne> personneData, Personne personne) {
        Personne _personne = personneData.get();
        _personne.setNom(personne.getNom());
        _personne.setPrenom(personne.getPrenom());
        _personne.setMail(personne.getMail());
        _personne.setMdp(passwordEncoder.encode(personne.getMdp()));
        _personne.setRoles(personne.getRoles());
        System.out.println("Professionnel modifié");
        return _personne;
    }

    public List<Personne> getProfessionnels() {
        //List<Personne> professionnelsList = personneRepository.findProfessionnels();
        return null;
    }

    private Collection<? extends GrantedAuthority> authorities;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
}

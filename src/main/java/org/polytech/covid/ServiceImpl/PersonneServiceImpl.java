package org.polytech.covid.ServiceImpl;

import java.util.Optional;

import org.polytech.covid.Entity.Personne;
import org.polytech.covid.Repository.PersonneRepository;
import org.polytech.covid.Service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonneServiceImpl implements PersonneService {

    @Autowired
    private PersonneRepository personneRepository;

    public Personne creerPersonne(Personne personne) {
        Personne _personne = personneRepository
                .save(new Personne(personne.getNom(), personne.getPrenom(), personne.getMail(), personne.getTelephone(),
                        personne.getAdresse()));
        return _personne;
    }

    public Personne modifierPersonne(Optional<Personne> personneData, Personne personne) {
        Personne _personne = personneData.get();
        _personne.setNom(personne.getNom());
        _personne.setPrenom(personne.getPrenom());
        _personne.setMail(personne.getMail());
        _personne.setTelephone(personne.getTelephone());
        _personne.setAdresse(personne.getAdresse());
        return _personne;
    }

}

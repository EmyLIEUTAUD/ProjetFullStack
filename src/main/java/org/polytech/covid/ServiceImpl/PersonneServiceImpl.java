package org.polytech.covid.ServiceImpl;

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
                        personne.getAdresse(), personne.getRole()));
        return _personne;
    }

}

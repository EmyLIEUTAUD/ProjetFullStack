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
    @Override
    public Personne save(Personne personne) {
        try {
             Personne savePersonne = personneRepository
                    .save(new Personne(personne.getNom(), personne.getPrenom(), personne.getMail(), personne.getTelephone(),
                            personne.getAdresse(),
                            personne.getRole()));
            return savePersonne;
        } catch (Exception e) {
            return null;
        }
    }
}

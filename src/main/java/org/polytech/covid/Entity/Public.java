package org.polytech.covid.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Public {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_personne;

    @OneToOne
    @JoinColumn(name = "identifiant", referencedColumnName = "identifiant")
    private Personne personne;

    private Integer dose;

}

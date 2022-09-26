package org.polytech.covid.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_admin;

    @OneToOne
    @JoinColumn(name = "identifiant", referencedColumnName = "identifiant")
    private Personne personne;

    @OneToOne
    @JoinColumn(name = "id_centre", referencedColumnName = "id_centre")
    private Centre centre;

}

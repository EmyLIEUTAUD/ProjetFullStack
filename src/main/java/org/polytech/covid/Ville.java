package org.polytech.covid;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Ville {

    @Id
    private Integer id_ville;
    private String nom;

}

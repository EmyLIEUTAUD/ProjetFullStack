package org.polytech.covid;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Centre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_centre;
    private String nom;
    private Integer id_ville;

}

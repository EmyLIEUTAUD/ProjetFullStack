package org.polytech.covid;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Centre {

    @Id
    private Integer id_centre;
    private String nom;
    private Integer id_ville;

}

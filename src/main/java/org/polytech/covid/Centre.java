package org.polytech.covid;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Centre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_centre;
    private String nom;

    @OneToOne
    @JoinColumn(name = "id_ville", referencedColumnName = "id_ville")
    private Ville id_ville;

}

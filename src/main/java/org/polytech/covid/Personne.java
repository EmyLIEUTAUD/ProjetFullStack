package org.polytech.covid;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Personne {

    @Id
    private Integer identifiant;
    private String nom;
    private String prenom;
    private String mail;
    private Integer telephone;
    private String adresse;
    private String role;

}

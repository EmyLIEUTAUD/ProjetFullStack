package org.polytech.covid.Entity;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
public class Personne {

    /***
     * Table contenant les Personnes (professionnels, patients, admins, médecins et
     * superAdmin)
     ***/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer identifiant;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
    private String mail;
    @Column
    private String mdp;

    @ElementCollection(fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private List<String> roles;

    public Personne() {

    }

    // Contructeur pour public
    public Personne(String nom, String prenom, String mail) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
    }

    // Constructeur pour un professionnel
    public Personne(String nom, String prenom, String mail, String mdp,
            List<String> roles) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.mdp = mdp;
        this.roles = roles;
    }

    public Integer getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(Integer identifiant) {
        this.identifiant = identifiant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        String name = nom.substring(0, 1).toUpperCase() + nom.substring(1).toLowerCase();
        this.nom = name;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "Personne [identifiant=" + identifiant + ", nom=" + nom + ", prenom=" + prenom + ", mail=" + mail
                + ", mdp=" + mdp + ", roles=" + roles + "]";
    }

}

package org.polytech.covid.Entity;

import javax.persistence.*;

@Entity
public class Centre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gid;
    private String nom;
    private String comnom;


    public Centre(Integer gid, String nom, String comnom) {
        this.gid = gid;
        this.nom = nom;
        this.comnom = comnom;
    }

    public Centre() {

    }

    public Integer getGid() {
        return gid;
    }

    public void setGid(Integer gid) {
        this.gid = gid;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getComnom() {
        return comnom;
    }

    public void setNomcom(String comnom) {
        this.comnom = comnom;
    }
}

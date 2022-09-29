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

    public Integer getNumAdresse() {
        return numAdresse;
    }

    public void setNumAdresse(Integer numAdresse) {
        this.numAdresse = numAdresse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getHorairesLundi() {
        return horairesLundi;
    }

    public void setHorairesLundi(String horairesLundi) {
        this.horairesLundi = horairesLundi;
    }

    public String getHorairesMardi() {
        return horairesMardi;
    }

    public void setHorairesMardi(String horairesMardi) {
        this.horairesMardi = horairesMardi;
    }

    public String getHorairesMercredi() {
        return horairesMercredi;
    }

    public void setHorairesMercredi(String horairesMercredi) {
        this.horairesMercredi = horairesMercredi;
    }

    public String getHorairesJeudi() {
        return horairesJeudi;
    }

    public void setHorairesJeudi(String horairesJeudi) {
        this.horairesJeudi = horairesJeudi;
    }

    public String getHorairesVendredi() {
        return horairesVendredi;
    }

    public void setHorairesVendredi(String horairesVendredi) {
        this.horairesVendredi = horairesVendredi;
    }

    public String getHorairesSamedi() {
        return horairesSamedi;
    }

    public void setHorairesSamedi(String horairesSamedi) {
        this.horairesSamedi = horairesSamedi;
    }

    public String getHorairesDimanche() {
        return horairesDimanche;
    }

    public void setHorairesDimanche(String horairesDimanche) {
        this.horairesDimanche = horairesDimanche;
    }
}

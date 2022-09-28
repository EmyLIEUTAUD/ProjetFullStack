package org.polytech.covid.Entity;

import javax.persistence.*;

@Entity
public class Centre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_centre;
    private String nom;

    private String ville;

    private Integer numAdresse;
    private String adresse;
    private Integer cp;
    private String horairesLundi;
    private String horairesMardi;
    private String horairesMercredi;
    private String horairesJeudi;
    private String horairesVendredi;
    private String horairesSamedi;
    private String horairesDimanche;

    public Centre() {

    }

    public Centre(Integer id_centre, String nom, String ville, Integer numAdresse, String adresse, Integer cp,
            String horairesLundi, String horairesMardi, String horairesMercredi, String horairesJeudi,
            String horairesVendredi, String horairesSamedi, String horairesDimanche) {
        this.id_centre = id_centre;
        this.nom = nom;
        this.ville = ville;
        this.numAdresse = numAdresse;
        this.adresse = adresse;
        this.cp = cp;
        this.horairesLundi = horairesLundi;
        this.horairesMardi = horairesMardi;
        this.horairesMercredi = horairesMercredi;
        this.horairesJeudi = horairesJeudi;
        this.horairesVendredi = horairesVendredi;
        this.horairesSamedi = horairesSamedi;
        this.horairesDimanche = horairesDimanche;
    }

    public Integer getId_centre() {
        return id_centre;
    }

    public void setId_centre(Integer id_centre) {
        this.id_centre = id_centre;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
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

    public Integer getCp() {
        return cp;
    }

    public void setCp(Integer cp) {
        this.cp = cp;
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

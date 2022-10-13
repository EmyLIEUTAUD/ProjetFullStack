package org.polytech.covid.Entity;

import javax.persistence.*;

@Entity
public class Centre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer gid;
    @Column(nullable = false)
    private String nom;
    @Column(nullable = false)
    private String comnom;
    private String numAdresse;
    @Column(nullable = false)
    private String adresse;
    @Column(nullable = false)
    private Integer cp;
    private String horairesLundi;
    private String horairesMardi;
    private String horairesMercredi;
    private String horairesJeudi;
    private String horairesVendredi;
    private String horairesSamedi;
    private String horairesDimanche;

    public Centre(String nom, String comnom, String numAdresse, String adresse, Integer cp, String horairesLundi,
            String horairesMardi, String horairesMercredi, String horairesJeudi, String horairesVendredi,
            String horairesSamedi, String horairesDimanche) {
        this.nom = nom;
        this.comnom = comnom;
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

    public void setComnom(String comnom) {
        this.comnom = comnom;
    }

    public String getNumAdresse() {
        return numAdresse;
    }

    public void setNumAdresse(String numAdresse) {
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

    @Override
    public String toString() {
        return "Centre [adresse=" + adresse + ", comnom=" + comnom + ", cp=" + cp + ", gid=" + gid
                + ", horairesDimanche=" + horairesDimanche + ", horairesJeudi=" + horairesJeudi + ", horairesLundi="
                + horairesLundi + ", horairesMardi=" + horairesMardi + ", horairesMercredi=" + horairesMercredi
                + ", horairesSamedi=" + horairesSamedi + ", horairesVendredi=" + horairesVendredi + ", nom=" + nom
                + ", numAdresse=" + numAdresse + "]";
    }

}

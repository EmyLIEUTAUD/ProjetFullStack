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
    private String adresse;
    private Integer cp;
    private String horairesLundi;
    private String horairesMardi;
    private String horairesMercredi;
    private String horairesJeudi;
    private String horairesVendredi;
    private String horairesSamedi;
    private String horairesDimanche;

    // Champs inutiles pour nous :
    private String arrete_pref_numero;
    private String xy_precis;
    private String id_adr;
    private String com_insee;
    private String lat_coor1;
    private String long_coor1;
    private String structure_siren;
    private String structure_type;
    private String structure_rais;
    private String structure_num;
    private String structure_voie;
    private String structure_cp;
    private String structure_insee;
    private String structure_com;
    private String _userid_creation;
    private String _userid_modification;
    private String _edit_datemaj;
    private String lieu_accessibilite;
    private String rdv;
    private String date_fermeture;
    private String date_ouverture;
    @Column(columnDefinition = "varchar(5000)")
    private String rdv_site_web;
    private String rdv_tel;
    private String rdv_tel2;
    @Column(columnDefinition = "varchar(5000)")
    private String rdv_modalites;
    private String rdv_consultation_prevaccination;
    private String centre_svi_repondeur;
    private String centre_fermeture;
    private String reserve_professionels_sante;
    private String centre_type;

    public Centre(Integer gid, String nom, String arrete_pref_numero, String xy_precis, String id_adr,
            String numAdresse, String adresse, Integer cp, String com_insee, String comnom, String lat_coor1,
            String long_coor1, String structure_siren, String structure_type, String structure_rais,
            String structure_num, String structure_voie, String structure_cp, String structure_insee,
            String structure_com, String _userid_creation, String _userid_modification, String _edit_datemaj,
            String lieu_accessibilite, String horairesLundi, String horairesMardi, String horairesMercredi,
            String horairesJeudi, String horairesVendredi, String horairesSamedi, String horairesDimanche, String rdv,
            String date_fermeture, String date_ouverture, String rdv_site_web, String rdv_tel, String rdv_tel2,
            String rdv_modalites, String rdv_consultation_prevaccination, String centre_svi_repondeur,
            String centre_fermeture, String reserve_professionels_sante, String centre_type) {
        this.gid = gid;
        this.nom = nom;
        this.arrete_pref_numero = arrete_pref_numero;
        this.xy_precis = xy_precis;
        this.id_adr = id_adr;
        this.numAdresse = numAdresse;
        this.adresse = adresse;
        this.cp = cp;
        this.com_insee = com_insee;
        this.comnom = comnom;
        this.lat_coor1 = lat_coor1;
        this.long_coor1 = long_coor1;
        this.structure_siren = structure_siren;
        this.structure_type = structure_type;
        this.structure_rais = structure_rais;
        this.structure_num = structure_num;
        this.structure_voie = structure_voie;
        this.structure_cp = structure_cp;
        this.structure_insee = structure_insee;
        this.structure_com = structure_com;
        this._userid_creation = _userid_creation;
        this._userid_modification = _userid_modification;
        this._edit_datemaj = _edit_datemaj;
        this.lieu_accessibilite = lieu_accessibilite;
        this.horairesLundi = horairesLundi;
        this.horairesMardi = horairesMardi;
        this.horairesMercredi = horairesMercredi;
        this.horairesJeudi = horairesJeudi;
        this.horairesVendredi = horairesVendredi;
        this.horairesSamedi = horairesSamedi;
        this.horairesDimanche = horairesDimanche;
        this.rdv = rdv;
        this.date_fermeture = date_fermeture;
        this.date_ouverture = date_ouverture;
        this.rdv_site_web = rdv_site_web;
        this.rdv_tel = rdv_tel;
        this.rdv_tel2 = rdv_tel2;
        this.rdv_modalites = rdv_modalites;
        this.rdv_consultation_prevaccination = rdv_consultation_prevaccination;
        this.centre_svi_repondeur = centre_svi_repondeur;
        this.centre_fermeture = centre_fermeture;
        this.reserve_professionels_sante = reserve_professionels_sante;
        this.centre_type = centre_type;
    }

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

    public Centre(Integer gid, String nom, String comnom, String numAdresse, String adresse, Integer cp,
            String horairesLundi,
            String horairesMardi, String horairesMercredi, String horairesJeudi, String horairesVendredi,
            String horairesSamedi, String horairesDimanche) {
        this.gid = gid;
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

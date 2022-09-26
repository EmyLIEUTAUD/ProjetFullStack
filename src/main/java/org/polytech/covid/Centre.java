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

    private String adresse;
    private String horairesLundi;
    private String horairesMardi;
    private String horairesMercredi;
    private String horairesJeudi;
    private String horairesVendredi;
    private String horairesSamedi;
    private String horairesDimanche;

    public Centre(Integer id_centre, String nom, Ville id_ville, String adresse, String horairesLundi,
            String horairesMardi, String horairesMercredi, String horairesJeudi, String horairesVendredi,
            String horairesSamedi, String horairesDimanche) {
        this.id_centre = id_centre;
        this.nom = nom;
        this.id_ville = id_ville;
        this.adresse = adresse;
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

    public Ville getId_ville() {
        return id_ville;
    }

    public void setId_ville(Ville id_ville) {
        this.id_ville = id_ville;
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

import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { VaccinationAdminServiceService } from '../vaccination-admin-service.service';
import { ProfessionnelsService } from '../professionnels.service';
import { User } from '../_models/user';
import { ChoixCentre } from '../choix-centre/choix-centre';
import { MedecinsService } from '../medecins.service';
import { Role } from '../_models/role';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  currentUser: any;
  personne: User = {identifiant: 0, nom: "", prenom: "", username: "", password: "", role: Role.Medecin};
  centre: ChoixCentre = {gid: 0, nom: "", comnom: "", numAdresse: "", adresse: "", horairesDimanche: "", horairesJeudi: "", horairesLundi: "", horairesMardi: "", horairesMercredi: "", horairesSamedi: "", horairesVendredi: "", cp: 0};
  showCentre: Boolean = false;
  hasRole : Boolean = false;

  constructor(private token: TokenStorageService, 
    private professionnelsService: ProfessionnelsService,
    private adminService: VaccinationAdminServiceService,
    private medecinService: MedecinsService) { }

  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    if(this.currentUser.authorities == "ADMIN"){
      this.showCentre = true;
      this.hasRole = true;
      this.professionnelsService.getProfessionnelByEmail(this.currentUser.sub).then((resultPersonne) => {
        this.personne = resultPersonne;
        console.log("id personne : "+ this.personne.identifiant);
        this.adminService.getVaccinationAdminByPersonneIdentifiant(this.personne.identifiant).subscribe((resultAdmin) => {
          this.centre = resultAdmin.centre;
          console.log(this.centre);
        })
      });
    }
    else if(this.currentUser.authorities == "MEDECIN"){
      this.showCentre = true;
      this.hasRole = true;
      this.professionnelsService.getProfessionnelByEmail(this.currentUser.sub).then((resultPersonne) => {
        this.personne = resultPersonne;
        console.log("id personne : "+ this.personne.identifiant);
        this.medecinService.getMedecinByPersonneIdentifiant(this.personne.identifiant).subscribe((resultMedecin) => {
          this.centre = resultMedecin.centre;
          console.log(this.centre);
        })
      });
    }
    else if(this.currentUser.authorities == "SUPER_ADMIN"){
      this.showCentre = false;
      this.hasRole = true;
      this.professionnelsService.getProfessionnelByEmail(this.currentUser.sub).then((resultPersonne) => {
        this.personne = resultPersonne;
        console.log("id personne : "+ this.personne.identifiant);
      });
    }
  }
}

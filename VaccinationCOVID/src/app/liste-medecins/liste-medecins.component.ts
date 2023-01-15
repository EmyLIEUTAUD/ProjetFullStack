import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { ChoixCentre } from '../choix-centre/choix-centre';
import { ChoixMedecin } from '../choix-medecin/choix-medecin';
import { MedecinsService } from '../medecins.service';
import { ProfessionnelsService } from '../professionnels.service';
import { VaccinationAdminServiceService } from '../vaccination-admin-service.service';
import { Role } from '../_models/role';
import { User } from '../_models/user';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-liste-medecins',
  templateUrl: './liste-medecins.component.html',
  styleUrls: ['./liste-medecins.component.scss']
})
export class ListeMedecinsComponent implements OnInit {

  
  selected?: ChoixMedecin;
  isLoggedIn = false;
  isLoginFailed = false;
  roles: string;
  isAdmin = false;
  isSuccessful = false;
  medecins: any = [];
  centre: ChoixCentre = {gid: 0, nom: "", comnom: "", numAdresse: "", adresse: "", horairesDimanche: "", horairesJeudi: "", horairesLundi: "", horairesMardi: "", horairesMercredi: "", horairesSamedi: "", horairesVendredi: "", cp: 0};
  currentUser: any;
  personne: User = {identifiant: 0, nom: "", prenom: "", username: "", password: "", role: Role.Admin};

  constructor(private router : Router,
    public modalRef: MdbModalRef<ListeMedecinsComponent>,
    private medecinsService: MedecinsService,
    private route: ActivatedRoute,
    private token: TokenStorageService,
    private professionnelsService: ProfessionnelsService,
    private adminService: VaccinationAdminServiceService) { }

    ngOnInit() {
      this.currentUser = this.token.getUser();
      this.professionnelsService.getProfessionnelByEmail(this.currentUser.sub).then((resultPersonne) => {
        this.personne = resultPersonne;
        this.adminService.getVaccinationAdminByPersonneIdentifiant(this.personne.identifiant).subscribe((resultAdmin) => {
          this.centre = resultAdmin.centre;
        })
      });
      this.loadMedecins();
    }
  
    loadMedecins(){
      return this.medecinsService.getAllMedecinsFromCenter().subscribe((resultMedecins:{})=>{
        this.medecins = resultMedecins;
      })   
    }

    modifierMedecin(medecin: ChoixMedecin){
      this.router.navigate(['editMedecin',medecin.id_medecin]);
    }

    ajouterMedecin(){
      this.router.navigate(['addMedecin']);
    }

  deleteMedecin(id_medecin: number){
    if (window.confirm('Are you sure, you want to delete?')) {
      this.medecinsService.deleteMedecin(id_medecin).subscribe((data) => {
        this.loadMedecins();
      });
    }
  }

}

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ChoixMedecin } from '../choix-medecin/choix-medecin';
import { MedecinsService } from '../medecins.service';
import { VaccinationCenterService } from '../vaccination-center.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-liste-medecins',
  templateUrl: './liste-medecins.component.html',
  styleUrls: ['./liste-medecins.component.scss']
})
export class ListeMedecinsComponent implements OnInit {

  medecins!: ChoixMedecin[];
  selected?: ChoixMedecin;
  isLoggedIn = false;
  isLoginFailed = false;
  roles: string;
  isAdmin = false;

  constructor(private router : Router,
    private service: MedecinsService,
    private route: ActivatedRoute,
    private tokenStorageService: TokenStorageService,) { }

  
  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    this.service.getAllMedecinsFromCenter().subscribe(resultMedecins=>{
      this.medecins = resultMedecins;
    });
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.authorities;
      this.isAdmin = this.roles.includes('ADMIN');
    }
  }

  modifierMedecin(medecin: ChoixMedecin){
    this.selected=medecin;
    console.log("Je veux modifier le médecin ",medecin);
    console.log("idMedecin : ",medecin.id_medecin);
    this.router.navigate(['editMedecin',medecin.id_medecin]);
  }

  supprimerMedecin(){

  }

}

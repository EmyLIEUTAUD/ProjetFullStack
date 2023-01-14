import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { ChoixMedecin } from '../choix-medecin/choix-medecin';
import { MedecinsService } from '../medecins.service';
import { ModalListMedecinsComponent } from '../modal-list-medecins/modal-list-medecins.component';
import { VaccinationCenterService } from '../vaccination-center.service';
import { Reservation } from '../_models/reservation';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-liste-medecins',
  templateUrl: './liste-medecins.component.html',
  styleUrls: ['./liste-medecins.component.scss']
})
export class ListeMedecinsComponent implements OnInit {

  //medecins!: ChoixMedecin[];
  selected?: ChoixMedecin;
  isLoggedIn = false;
  isLoginFailed = false;
  roles: string;
  isAdmin = false;
  isSuccessful = false;
  medecins: any = [];

  constructor(private router : Router,
    public modalRef: MdbModalRef<ListeMedecinsComponent>,
    private medecinsService: MedecinsService,
    private route: ActivatedRoute,
    private tokenStorageService: TokenStorageService,) { }

    ngOnInit() {
      this.loadMedecins();
    }
  
    loadMedecins(){
      return this.medecinsService.getAllMedecinsFromCenter().subscribe((resultMedecins:{})=>{
        this.medecins = resultMedecins;
      })   
    }

    modifierMedecin(medecin: ChoixMedecin){
      console.log("Je veux modifier le médecin ",medecin);
      console.log("idMedecin : ",medecin.id_medecin);
      this.router.navigate(['editMedecin',medecin.id_medecin]);
    }

    ajouterMedecin(){
      console.log("Je veux ajouter le médecin");
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

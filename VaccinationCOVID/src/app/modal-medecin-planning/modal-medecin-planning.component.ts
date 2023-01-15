import { Component, OnInit } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { Router } from '@angular/router';
import { PersonneService } from '../personne.service';
import { User } from '../_models/user';
import { Reservation } from '../_models/reservation';
import { MedecinsService } from '../medecins.service';
import { ChoixCentre } from '../choix-centre/choix-centre';
import { Role } from '../_models/role';
import { TokenStorageService } from '../_services/token-storage.service';
import { ProfessionnelsService } from '../professionnels.service';
@Component({
  selector: 'app-modal-medecin-planning',
  templateUrl: './modal-medecin-planning.component.html',
  styleUrls: ['./modal-medecin-planning.component.scss']
})
export class ModalMedecinPlanningComponent implements OnInit{
  nomPersonne : string;
  personnes!: User[];
  reservations!: Reservation[];
  public!: any;
  isValide = false;

  
  #prenom: string;
  #nom: string;
  #email: string;
  dateRdv: string;
  isSuccessful = false;
  day: number;
  jour: string;
  horaires : string;
  word = '';
  infos = '';
  isNotSuccessful = false;
  wait = false;
  ferme = false;
  form: any = {
    dateRdv:null
  };
  centre: ChoixCentre = {gid: 0, nom: "", comnom: "", numAdresse: "", adresse: "", horairesDimanche: "", horairesJeudi: "", horairesLundi: "", horairesMardi: "", horairesMercredi: "", horairesSamedi: "", horairesVendredi: "", cp: 0};
  currentUser: any;
  personne: User = {identifiant: 0, nom: "", prenom: "", username: "", password: "", role: Role.Medecin};

  constructor(public modalRef: MdbModalRef<ModalMedecinPlanningComponent>,
    private router : Router,
    private service: PersonneService,
    private medecinService: MedecinsService,
    private token: TokenStorageService,
    private professionnelsService: ProfessionnelsService,
    ) {}

    ngOnInit() :void{ 
      this.currentUser = this.token.getUser();
      this.professionnelsService.getProfessionnelByEmail(this.currentUser.sub).then((resultPersonne) => {
        this.personne = resultPersonne;
        console.log("id personne : "+ this.personne.identifiant);
        this.medecinService.getMedecinByPersonneIdentifiant(this.personne.identifiant).subscribe((resultMedecin) => {
          this.centre = resultMedecin.centre;
          console.log(this.centre);
        })
      });
      this.service._nomPersonneSubject.subscribe((nomPersonne) => {
        this.service.getReservationsByNom(nomPersonne).subscribe(resultReservations=>{
        this.reservations=resultReservations;
      });
      });
      
      
    }
    
    onSubmit(): void {
      console.log("je submit le form");
      const {dateRdv} = this.form;
      console.log(this.form);
      this.getReservationByDate(dateRdv);
  
    }
    searchByNom(nomPersonne: string){
      this.service.setNomPersonne(nomPersonne);
    }

    valider(id: any){
      if (window.confirm('Are you sure, you want to validate?'))
      this.service.validerReservation(id).subscribe((data)=>{
        this.public = data;
        this.isValide=true;
      })   
    }

    getReservationByDate(date: string){
      this.dateRdv = this.convertDate(date)
      return this.medecinService.getReservationByDate(this.dateRdv).subscribe((resultReservations)=>{
        this.reservations = resultReservations });
    }

    convertDate(date) { // convertion date en format yyyy-mm-dd
      var yyyy = date.getFullYear().toString();
      var mm = (date.getMonth()+1).toString();
      var dd  = date.getDate().toString();
    
      var mmChars = mm.split('');
      var ddChars = dd.split('');
    
      return yyyy + '-' + (mmChars[1]?mm:"0"+mmChars[0]) + '-' + (ddChars[1]?dd:"0"+ddChars[0]);
      }

}

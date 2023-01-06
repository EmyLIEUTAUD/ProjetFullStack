import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { textSpanContainsPosition } from 'typescript';
import { ChoixCentre } from '../choix-centre/choix-centre';
import { EnvoiFormulaireService } from '../envoi-formulaire.service';
import { VaccinationCenterService } from '../vaccination-center.service';
import { HttpClient, HttpHeaders, HttpHeaderResponse, HttpResponse } from '@angular/common/http';

@Component({
  selector: 'app-rendez-vous',
  templateUrl: './rendez-vous.component.html',
  styleUrls: ['./rendez-vous.component.scss']
})
export class RendezVousComponent implements OnInit {

  centre: ChoixCentre;
  prenom: string;
  nom: string;
  email: string;
  dateRdv: string;
  isSuccessful = false;
  day: number;
  jour: string;
  horaires : string;
  word = '';
  infos = '';
  isNotSuccessful = false;
  wait = false;

  constructor(private route: ActivatedRoute, private service: VaccinationCenterService,private service2: EnvoiFormulaireService, private readonly http: HttpClient, private readonly router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => this.service.getVaccinationCenterById(params['gid']).subscribe(resultCenters=> {
      this.centre = resultCenters;
      console.log("test centre"+this.centre.gid);
    }));
  }

  EnvoyerForm(centre: ChoixCentre,prenom: string,nom: string,email: string,dateRdv: string): void{
    dateRdv = this.convertDate(dateRdv);
    this.jour = this.GetDayOfDate(dateRdv);
    if(this.horaires != 'fermé'){
      this.service2.saveRdv(centre, prenom, nom, email, dateRdv);
      if(this.service2.flag == true && this.service2.end == true){
        console.log("c'est true");
        this.isSuccessful = true;
        this.isNotSuccessful = false;
        this.wait = false;
      }
      else if(this.service2.flag == false && this.service2.end == true){
        console.log("c'est false");
        this.isSuccessful = false;
        this.isNotSuccessful = false;
        this.infos = this.service2.infos;
        this.wait = true;
      }
    }
    else{
      this.isSuccessful = false;
      this.isNotSuccessful = true;
      this.wait = false;
    }

  }

  convertDate(date) { // convertion date en format yyyy-mm-dd
  var yyyy = date.getFullYear().toString();
  var mm = (date.getMonth()+1).toString();
  var dd  = date.getDate().toString();

  var mmChars = mm.split('');
  var ddChars = dd.split('');

  return yyyy + '-' + (mmChars[1]?mm:"0"+mmChars[0]) + '-' + (ddChars[1]?dd:"0"+ddChars[0]);
  }

  GetDayOfDate(dateRdv){
    var date = new Date(dateRdv);
    this.day = date.getDay()
    if(this.day == 1){
      this.horaires = this.centre.horairesLundi;
      return "lundi";
    }
    else if(this.day == 2){
      this.horaires = this.centre.horairesMardi;
      return "mardi";
    }
    else if(this.day == 3){
      this.horaires = this.centre.horairesMercredi;
      return "mercredi";
    }
    else if(this.day == 4){
      this.horaires = this.centre.horairesJeudi;
      return "jeudi";
    }
    else if(this.day == 5){
      this.horaires = this.centre.horairesVendredi;
      return "vendredi";
    }
    else if(this.day == 6){
      this.horaires = this.centre.horairesSamedi;
      return "samedi";
    }
    else{
      this.horaires = this.centre.horairesDimanche;
      return "dimanche";
    }
  }

}

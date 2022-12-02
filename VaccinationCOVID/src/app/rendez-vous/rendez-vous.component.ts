import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { textSpanContainsPosition } from 'typescript';
import { ChoixCentre } from '../choix-centre/choix-centre';
import { EnvoiFormulaireService } from '../envoi-formulaire.service';
import { VaccinationCenterService } from '../vaccination-center.service';

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
  constructor(private route: ActivatedRoute, private service: VaccinationCenterService,private service2: EnvoiFormulaireService) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => this.service.getVaccinationCenterById(params['gid']).subscribe(resultCenters=> {
      this.centre = resultCenters;
      console.log("test centre"+this.centre.gid);
    }));
  }

  EnvoyerForm(centre: ChoixCentre,prenom: string,nom: string,email: string,dateRdv: string): void{
    dateRdv = this.convertDate(dateRdv);
    var fin = this.service2.saveRdv(centre, prenom, nom, email, dateRdv);
    console.log("fichier fini : ", fin);
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
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
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
  constructor(private router : Router, private route: ActivatedRoute, private service: VaccinationCenterService,private service2: EnvoiFormulaireService) { }



  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => this.service.getVaccinationCenterById(params['gid']).subscribe(resultCenters=> {
      this.centre = resultCenters;
      console.log("test centre"+this.centre.gid);
    }));
  }

  fermeture = (d: Date): boolean => {
    var day = d.getDay();
    var lundi;
    var mardi;
    var mercredi;
    var jeudi;
    var vendredi;
    var samedi;
    var dimanche;
    console.log("jesus ",day!==1);
    console.log("jesus ",this.centre.horairesLundi);
    if (this.centre.horairesLundi=="fermé")
      lundi = false;
    else
      lundi = true;

    if (this.centre.horairesMardi=="fermé")
      mardi = false;
    else
      mardi = true;

    if (this.centre.horairesMercredi=="fermé")
      mercredi = false;
    else
      mercredi = true;

    if (this.centre.horairesJeudi=="fermé")
      jeudi = false;
    else
      jeudi = true;

    if (this.centre.horairesVendredi=="fermé")
      vendredi = false;
    else
      vendredi = true;

    if (this.centre.horairesSamedi=="fermé")
      samedi = false;
    else
      samedi = true;

    if (this.centre.horairesDimanche=="fermé")
      dimanche = false;
    else
      dimanche = true;
    return (lundi == false? day !== 0: '') && day !== 1 && day !== 2 && day !== 3 && day !== 4 && day !== 5 && day !== 6;
  }

  EnvoyerForm(centre: ChoixCentre,prenom: string,nom: string,email: string,dateRdv: string): void{
    dateRdv = this.convertDate(dateRdv);
    var fin = this.service2.saveRdv(centre, prenom, nom, email, dateRdv);
    console.log("fichier fini : ", fin);
    this.router.navigate(['validation',dateRdv]);
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
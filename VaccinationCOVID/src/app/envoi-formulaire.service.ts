import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChoixCentre } from './choix-centre/choix-centre';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class EnvoiFormulaireService {
  
  constructor(private  httpClient: HttpClient) { }

/*   saveRdv(centre: ChoixCentre,prenom: string,nom: string,mail: string,dateRdv: string){
    this.personne.prenom = prenom;
    this.personne.nom = nom;
    this.personne.mail = mail;
    console.log("personne"+this.personne);

    const inscription = JSON.stringify(centre.gid)+JSON.stringify(this.personne)+JSON.stringify(dateRdv);
    console.log("test"+inscription);

    this.httpClient.post("public/inscription",inscription);
    console.log(inscription);
  } */

  saveRdv(centre: ChoixCentre,prenom: string,nom: string,mail: string,date_reservation: string){

    const personne = {
      prenom: prenom,
      nom: nom,
      mail: mail
    };
    const inscription = {
      centre: centre,
      personne: personne,
      date_reservation: date_reservation
    };
    console.log("test : ",inscription);
    this.httpClient.post<string>("public/inscription",inscription).subscribe(
      data => {
         console.log("succés")
         console.log(data)
      }
   );
    /*return this.httpClient.post<String>("public/inscription",inscription1);
    console.log("test2",inscription);*/
  }
}

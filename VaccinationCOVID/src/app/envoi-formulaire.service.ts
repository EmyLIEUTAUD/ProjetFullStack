import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChoixCentre } from './choix-centre/choix-centre';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { resolve } from '@angular/compiler-cli';

@Injectable({
  providedIn: 'root'
})
export class EnvoiFormulaireService {

  word = '';
  infos = '';
  flag = false;
  end = false;
  
  constructor(private  httpClient: HttpClient, private router: Router) { }

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
    /*this.httpClient.post<string>("public/inscription",inscription).subscribe(
      data => {
         console.log("succés")
         console.log(data)
      }
   );*/
  
    console.log("infos :");
    let temps: any;
    return this.httpClient.post<Observable<string>>('/public/inscription/', inscription, {observe: 'response'})
    .subscribe({
      next: (resp) => {
      console.log("succès");
      console.log(resp);
      const keys = resp.headers.keys();
      console.log(keys);
      const nbToken =  resp.headers.get('X-Rate-Limit-Remaining')
      this.infos = `${nbToken} tokens restant`
      this.flag = true;
      this.end = true;
    },
    error:  (err) => {
      console.log("C'est une erreur");
      //this.isNotSuccessful = true;
      console.error(err);
      const keys = err.headers.keys();
      console.log(keys);
      temps =  err.headers.get('X-Rate-Limit-Retry-After-Seconds')
      this.infos = `Ressayer après ${temps} secondes`;
      this.flag = false;
      this.end = true;
    }
  });
  /*if(this.flag == true){
    console.log("flag is true");
    return true;
  }
  console.log("j'ai fini de contacter /public/inscription");
  return false;*/
    /*return this.httpClient.post<String>("public/inscription",inscription1);
    console.log("test2",inscription);*/
  }
}

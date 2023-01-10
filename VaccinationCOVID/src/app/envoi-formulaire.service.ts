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
  etag: Array<string>;
  
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

  async saveRdv(centre: ChoixCentre,prenom: string,nom: string,mail: string,date_reservation: string): Promise<void>{

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
    let flag2
    let flagPromise: Promise<void> = new Promise((flag => flag2 = flag))
    console.log("infos :");
    let temps: any;
    let ifMatch;
    if(this.etag != null){
      ifMatch = new HttpHeaders({"If-Match": this.etag});
    }
    this.httpClient.post<Observable<string>>('/public/inscription/', inscription, {observe: 'response', headers: ifMatch})
    .subscribe({
      next: (resp) => {
      console.log("succès");
      console.log("resp : " + resp);
      const keys = resp.headers.keys();
      console.log(keys);
      const nbToken =  resp.headers.get('X-Rate-Limit-Remaining')
      this.infos = `${nbToken} tokens restant`
      console.log(parseInt(nbToken))
      this.flag = true
      flag2(this.flag)
      this.etag = [resp.headers.get('ETag')];
      console.log("etag : "+this.etag);
      //this.flag = true;
      //this.end = true;
      },
      error:  (err) => {
        console.log("C'est une erreur");
        //this.isNotSuccessful = true;
        console.error(err);
        const keys = err.headers.keys();
        console.log("keys : "+keys);
        temps =  err.headers.get('X-Rate-Limit-Retry-After-Seconds')
        this.infos = `Ressayer après ${temps} secondes`;
        console.log("infos dans la requête : ", this.infos);
        this.flag = false;
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
    return flagPromise
  }
}

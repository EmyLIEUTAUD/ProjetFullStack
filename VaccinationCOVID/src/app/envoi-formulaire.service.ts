import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChoixCentre } from './choix-centre/choix-centre';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';

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
    let flag2
    let flagPromise: Promise<void> = new Promise((flag => flag2 = flag))
    console.log("infos :");
    let temps: any;
    let ifMatch;
    if(this.etag != null){
      ifMatch = new HttpHeaders({'Cache-Control': 'no-cache', "If-Match": this.etag});
    }
    this.httpClient.post<Observable<string>>('http://localhost:8080/public/inscription/', inscription, {observe: 'response', headers: ifMatch})
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
      
      },
      error:  (err) => {
        console.log("C'est une erreur");
        console.error(err);
        const keys = err.headers.keys();
        console.log("keys : "+keys);
        temps =  err.headers.get('X-Rate-Limit-Retry-After-Seconds')
        this.infos = `Ressayer après ${temps} secondes`;
        console.log("infos dans la requête : ", this.infos);
        this.flag = false;
        flag2(this.flag)
      }
    
  });
    return flagPromise
  }
}

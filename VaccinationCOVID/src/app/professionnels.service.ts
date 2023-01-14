import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './_models/user';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProfessionnelsService {

  personne: User;
  flag: Boolean = false;

  constructor(private  httpClient: HttpClient) { }

  getAllProfessionnels(){
    console.log("Je veux get tous les professionnels");
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    return this.httpClient.get<User[]>("admin/professionnels", {headers: headers});
  }

  getProfessionnelByEmail(email: String): Promise<User>{
    let flag2
    let flagPromise: Promise<User> = new Promise((flag => flag2 = flag))
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    this.httpClient.get<User>("admin/professionnels/email/"+email, {observe: 'body', headers: headers}).subscribe({
      next: (resultPersonne) => {
        this.personne = resultPersonne;
        console.log(this.personne);
        this.flag = true
        flag2(this.personne)
      }
    })
    return flagPromise;
  }

  addMedecinById(professionnel: any, etag: Array<string>): Observable<HttpResponse<User>>{
    let headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache', 'If-Match': etag});
    console.log("addMedecin : ",professionnel.identifiant);

    const Personne = {
      identifiant: professionnel.identifiant
    }

    const Ajout = {
      personne: Personne
    }

    return this.httpClient.post<User>("admin/medecins/nouveau", Ajout, {observe: "response", headers: headers});
  }

  addAdminById(professionnel: any,gidCentre:any, etag: Array<string>): Observable<HttpResponse<User>>{
    let headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache', 'If-Match': etag});
    console.log("addAdmin : ",professionnel.identifiant);

    const Personne = {
      identifiant: professionnel.identifiant
    }

    const ChoixCentre = {
      gid: gidCentre
    }
    const Ajout = {
      personne: Personne,
      centre : ChoixCentre
    }

    return this.httpClient.post<User>("admin/administrateurs/nouveau", Ajout, {observe: "response", headers: headers});
  }


}

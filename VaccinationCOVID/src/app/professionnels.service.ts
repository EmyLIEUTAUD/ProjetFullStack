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
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    return this.httpClient.get<User[]>("http://localhost:8080/admin/professionnels", {headers: headers});
  }

  getProfessionnelByEmail(email: String): Promise<User>{
    let flag2
    let flagPromise: Promise<User> = new Promise((flag => flag2 = flag))
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    this.httpClient.get<User>("http://localhost:8080/admin/professionnels/email/"+email, {observe: 'body', headers: headers}).subscribe({
      next: (resultPersonne) => {
        this.personne = resultPersonne;
        this.flag = true
        flag2(this.personne)
      }
    })
    return flagPromise;
  }

  addMedecinById(professionnel: any, etag: Array<string>): Observable<HttpResponse<User>>{
    let headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache', 'If-Match': etag});

    const Personne = {
      identifiant: professionnel.identifiant
    }

    const Ajout = {
      personne: Personne
    }

    return this.httpClient.post<User>("http://localhost:8080/admin/medecins/nouveau", Ajout, {observe: "response", headers: headers});
  }

  addAdminById(professionnel: any,gidCentre:any, etag: Array<string>): Observable<HttpResponse<User>>{
    let headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache', 'If-Match': etag});

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

    return this.httpClient.post<User>("http://localhost:8080/admin/administrateurs/nouveau", Ajout, {observe: "response", headers: headers});
  }


}

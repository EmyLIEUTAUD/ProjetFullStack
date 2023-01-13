import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './_models/user';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProfessionnelsService {

  constructor(private  httpClient: HttpClient) { }

  getAllProfessionnels(){
    console.log("Je veux get tous les professionnels");
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    return this.httpClient.get<User[]>("admin/professionnels", {headers: headers});
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

}

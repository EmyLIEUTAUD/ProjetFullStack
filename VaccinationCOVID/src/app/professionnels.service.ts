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
    console.log("addMedecin : ",professionnel);
    return this.httpClient.put<User>("admin/medecins/nouveau", JSON.stringify(professionnel), {observe: "response", headers: headers});
  }

}

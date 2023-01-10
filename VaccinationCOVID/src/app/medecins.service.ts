import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ChoixMedecin } from './choix-medecin/choix-medecin';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MedecinsService {

  constructor(private  httpClient: HttpClient) { }

  getAllMedecinsFromCenter(){
    const headers = new HttpHeaders({'Content-Type': 'application/json'})
    return this.httpClient.get<ChoixMedecin[]>("admin/medecins", {headers: headers});
  }

  getMedecinById(id_medecin: number) : Observable<ChoixMedecin>{
    const headers = new HttpHeaders({'Content-Type': 'application/json'})
    console.log("id_medecin dans le service : ",id_medecin);
    return this.httpClient.get<ChoixMedecin>("admin/medecins/id/"+id_medecin, {headers: headers});
  }

  editMedecinById(idMedecin: number, editMedecin: any, etag: Array<string>): Observable<HttpResponse<ChoixMedecin>>{
    let headers = new HttpHeaders({'Content-Type': 'application/json', 'If-Match': etag});
    console.log("editMedecin : ",editMedecin);
    return this.httpClient.put<ChoixMedecin>("admin/medecins/modifier/"+idMedecin, JSON.stringify(editMedecin), {observe: "response", headers: headers});
  }

}

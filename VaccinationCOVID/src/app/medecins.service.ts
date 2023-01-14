import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { ChoixMedecin } from './choix-medecin/choix-medecin';
import { Observable } from 'rxjs';
import { Reservation } from './_models/reservation';

@Injectable({
  providedIn: 'root'
})
export class MedecinsService {

  constructor(private  httpClient: HttpClient) { }

  getAllMedecinsFromCenter(){
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    return this.httpClient.get<ChoixMedecin[]>("admin/medecins", {headers: headers});
  }

  getMedecinById(id_medecin: number) : Observable<ChoixMedecin>{
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    console.log("id_medecin dans le service : ",id_medecin);
    return this.httpClient.get<ChoixMedecin>("admin/medecins/id/"+id_medecin, {headers: headers});
  }

  editMedecinById(idMedecin: number, editMedecin: any, etag: Array<string>): Observable<HttpResponse<ChoixMedecin>>{
    let headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache', 'If-Match': etag});
    console.log("editMedecin : ",editMedecin);
    return this.httpClient.put<ChoixMedecin>("admin/medecins/modifier/"+idMedecin, JSON.stringify(editMedecin), {observe: "response", headers: headers});
  }

  deleteMedecin(idMedecin: number){
    return this.httpClient.delete<ChoixMedecin>("admin/medecins/supprimer/"+idMedecin);
  }

  getReservationByDate(date: string){
    let headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    return this.httpClient.get<Reservation[]>("admin/reservations/centre/"+date, {headers: headers});
  }

}

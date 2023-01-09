import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { ChoixCentre } from './choix-centre/choix-centre';
import { HttpHeaders  } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VaccinationCenterService {

/*CENTERS: ChoixCentre[] = [
    {gid:1, name:"hopital central", city: "nancy", numAdresse: "2 rue", adresse: "3", cp: 5, horairesLundi: "a", horairesMardi: "a", horairesMercredi: "a", horairesJeudi: "a", horairesVendredi: "a", horairesSamedi: "a", horairesDimanche: "a"},
    {gid:2, name:"truc central", city: "nancy", numAdresse: "2 rue", adresse: "3", cp: 5, horairesLundi: "a", horairesMardi: "a", horairesMercredi: "a", horairesJeudi: "a", horairesVendredi: "a", horairesSamedi: "a", horairesDimanche: "a"},
    {gid:3, name:"machin central", city: "paris", numAdresse: "2 rue", adresse: "4", cp: 5, horairesLundi: "a", horairesMardi: "a", horairesMercredi: "a", horairesJeudi: "a", horairesVendredi: "a", horairesSamedi: "a", horairesDimanche: "a"},
    {gid:4, name:"chose central", city: "paris", numAdresse: "2 rue", adresse: "5", cp: 5, horairesLundi: "a", horairesMardi: "a", horairesMercredi: "a", horairesJeudi: "a", horairesVendredi: "a", horairesSamedi: "a", horairesDimanche: "a"}
  ];*/

  constructor(private  httpClient: HttpClient) { }

  getAllVaccinationCenter(comnom: string) : Observable<ChoixCentre[]>{
    const httpOptions = {
      headers: new HttpHeaders(
        { 
         
           'Content-Type': 'application/json'
        })
    }
    return this.httpClient.get<ChoixCentre[]>("public/centres/"+comnom, httpOptions);
  }
  getVaccinationCenterById(gid: number) : Observable<ChoixCentre>{
    return this.httpClient.get<ChoixCentre>("public/centres/id/"+gid);
  }
  editVaccinationCentreById(gid: number, editCentre: any): Observable<ChoixCentre>{
    let headers = new HttpHeaders({'Content-Type': 'application/json'});

    return this.httpClient.put<ChoixCentre>("http://localhost:8080/admin/centres/modifier/"+gid, JSON.stringify(editCentre), {headers: headers});
  }
}
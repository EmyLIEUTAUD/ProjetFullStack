import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { ChoixCentre } from './choix-centre/choix-centre';

@Injectable({
  providedIn: 'root'
})
export class VaccinationCenterService {

CENTERS: ChoixCentre[] = [
    {id:1, name:"hopital central", city: "nancy", numAdresse: "2 rue", adresse: "3", cp: 5, horairesLundi: "a", horairesMardi: "a", horairesMercredi: "a", horairesJeudi: "a", horairesVendredi: "a", horairesSamedi: "a", horairesDimanche: "a"},
    {id:2, name:"truc central", city: "nancy", numAdresse: "2 rue", adresse: "3", cp: 5, horairesLundi: "a", horairesMardi: "a", horairesMercredi: "a", horairesJeudi: "a", horairesVendredi: "a", horairesSamedi: "a", horairesDimanche: "a"},
    {id:3, name:"machin central", city: "paris", numAdresse: "2 rue", adresse: "4", cp: 5, horairesLundi: "a", horairesMardi: "a", horairesMercredi: "a", horairesJeudi: "a", horairesVendredi: "a", horairesSamedi: "a", horairesDimanche: "a"},
    {id:4, name:"chose central", city: "paris", numAdresse: "2 rue", adresse: "5", cp: 5, horairesLundi: "a", horairesMardi: "a", horairesMercredi: "a", horairesJeudi: "a", horairesVendredi: "a", horairesSamedi: "a", horairesDimanche: "a"}
  ];

  constructor(private  httpClient: HttpClient) { }

  getAllVaccinationCenter() : Observable<ChoixCentre[]>{
    console.log('service');
    return this.httpClient.get<ChoixCentre[]>("*/public/centres/Nancy");
  }
}

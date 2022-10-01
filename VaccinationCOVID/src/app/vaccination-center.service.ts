import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { ChoixCentre } from './choix-centre/choix-centre';

@Injectable({
  providedIn: 'root'
})
export class VaccinationCenterService {

/*CENTERS: ChoixCentre[]=[
    {id:1, name: "Hôpital central", address: "Rue lionnois", postalCode: "54000", city: "Nancy"},
    {id:2, name: "Grand centre de vaccination", address: "45, avenue", postalCode: "54000", city: "Nancy"}
  ]*/

  constructor(private  httpClient: HttpClient) { }

  getAllVaccinationCenter() : Observable<ChoixCentre[]>{
    return this.httpClient.get<ChoixCentre[]>("k");
  }
}

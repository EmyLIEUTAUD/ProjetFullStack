import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './_models/user';
import { Subject } from 'rxjs';
import { Reservation } from './_models/reservation';
import { Public } from './_models/public';

@Injectable({
  providedIn: 'root'
})
export class PersonneService {

  nomPersonne: string;
  _nomPersonneSubject: Subject<string> = new Subject<string>(); // utiliser comme file d'attente pour actualiser nomPersonne

  constructor(private  httpClient: HttpClient) { }

  getReservationsByNom(nom: string): Observable<Reservation[]>{
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    return this.httpClient.get<Reservation[]>("admin/personnes/"+nom, {headers: headers});
  }

  setNomPersonne(nomPersonne: string){
    this.nomPersonne=nomPersonne;
    this._nomPersonneSubject.next(nomPersonne); // ajoute nomPersonne dans la liste d'attente
  }
  getNomPersonne() : string{
    return this.nomPersonne;
  }

  validerReservation(id: any){
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    return this.httpClient.put("admin/personnes/validerVaccination/"+id, {headers: headers});
  }

}

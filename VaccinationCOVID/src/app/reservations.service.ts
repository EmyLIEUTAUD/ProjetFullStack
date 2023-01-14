import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reservation } from './_models/reservation';
import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ReservationsService {

  constructor(private  httpClient: HttpClient) { }

  getAllReservationsFromCenter(){
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    return this.httpClient.get<Reservation[]>("http://localhost:8080/admin/reservations", {headers: headers});
  }

  getReservationById(id_reservation: number) : Observable<Reservation>{
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    console.log("id_reservation dans le service : ",id_reservation);
    return this.httpClient.get<Reservation>("http://localhost:8080/admin/reservations/id/"+id_reservation, {headers: headers});
  }

  deleteReservation(idReservation: number){
    return this.httpClient.delete<Reservation>("http://localhost:8080/admin/reservations/supprimer/"+idReservation);
  }

}

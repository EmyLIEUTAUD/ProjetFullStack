import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { ReservationsService } from '../reservations.service';
import { Reservation } from '../_models/reservation';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-liste-reservations',
  templateUrl: './liste-reservations.component.html',
  styleUrls: ['./liste-reservations.component.scss']
})
export class ListeReservationsComponent {

  reservations: any = [];

  constructor(private router : Router,
    public modalRef: MdbModalRef<ListeReservationsComponent>,
    private reservationsService: ReservationsService,
    private route: ActivatedRoute,
    private tokenStorageService: TokenStorageService,) { }

    ngOnInit() {
      this.loadReservations();
    }
  
    loadReservations(){
      return this.reservationsService.getAllReservationsFromCenter().subscribe((resultReservations:{})=>{
        this.reservations = resultReservations;
      })   
    }

    modifierReservation(reservation: Reservation){
      console.log("Je veux modifier la réservation ",reservation);
      console.log("idReservation : ",reservation.id_reservation);
      this.router.navigate(['editReservation',reservation.id_reservation]);
    }

  deleteReservation(id_reservation: number){
    if (window.confirm('Are you sure, you want to delete?')) {
      this.reservationsService.deleteReservation(id_reservation).subscribe((data) => {
        this.loadReservations();
      });
    }
  }

}

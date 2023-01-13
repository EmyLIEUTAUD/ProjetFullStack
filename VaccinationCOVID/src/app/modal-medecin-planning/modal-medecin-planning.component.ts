import { Component, OnInit } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { Router } from '@angular/router';
import { PersonneService } from '../personne.service';
import { User } from '../_models/user';
import { Reservation } from '../_models/reservation';
import { Public } from '../_models/public';

@Component({
  selector: 'app-modal-medecin-planning',
  templateUrl: './modal-medecin-planning.component.html',
  styleUrls: ['./modal-medecin-planning.component.scss']
})
export class ModalMedecinPlanningComponent implements OnInit{
  nomPersonne : string;
  personnes!: User[];
  reservations!: Reservation[];
  public!: any;
  isValide = false;

  constructor(public modalRef: MdbModalRef<ModalMedecinPlanningComponent>,
    private router : Router,
    private service: PersonneService,
    ) {}

    ngOnInit() :void{ 
      this.service._nomPersonneSubject.subscribe((nomPersonne) => {
        this.service.getReservationsByNom(nomPersonne).subscribe(resultReservations=>{
        this.reservations=resultReservations;
      });
      });
      
    }

    searchByNom(nomPersonne: string){
      this.service.setNomPersonne(nomPersonne);
    }

    valider(id: any){
      if (window.confirm('Are you sure, you want to validate?'))
      this.service.validerReservation(id).subscribe((data)=>{
        this.public = data;
        this.isValide=true;
      })
      

    }




}

import { Component, OnInit } from '@angular/core';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { ModalListMedecinsComponent } from '../modal-list-medecins/modal-list-medecins.component';
import { ModalListReservationsComponent } from '../modal-list-reservations/modal-list-reservations.component';



@Component({  
    selector: 'app-board-admin',
    templateUrl: './board-admin.component.html' })
export class BoardAdminComponent implements OnInit {
  modalRefReservations: MdbModalRef<ModalListReservationsComponent> | null = null;
  modalRefMedecins: MdbModalRef<ModalListMedecinsComponent> | null = null;

  ifSuccessful = false;

  allMedecins;
  allReservations;
  constructor(private modalService: MdbModalService) {}

  openModalSuperAdmin() {
    this.modalRefReservations = this.modalService.open(ModalListMedecinsComponent)
  }
  openModalConfig() {
    this.modalRefMedecins = this.modalService.open(ModalListReservationsComponent)
  }
  
  ngOnInit(): void{
     
  }
    
}

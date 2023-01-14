import { Component, OnInit } from '@angular/core';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { ModalMedecinPlanningComponent } from '../modal-medecin-planning/modal-medecin-planning.component';


@Component({
  selector: 'app-board-medecin',
  templateUrl: './board-medecin.component.html',
  styleUrls: ['./board-medecin.component.scss']
})
export class BoardMedecinComponent implements OnInit {
  content?: string;
  modalRef: MdbModalRef<ModalMedecinPlanningComponent> | null = null;


  constructor(private modalService: MdbModalService,
    ) {}

  ngOnInit(): void {
    
  }
  
  openModalPlanning() {
    this.modalRef = this.modalService.open(ModalMedecinPlanningComponent)
  }

 

}

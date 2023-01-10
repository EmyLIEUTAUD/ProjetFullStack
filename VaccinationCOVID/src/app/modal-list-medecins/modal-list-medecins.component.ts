import { Component, OnInit } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';

@Component({
  selector: 'app-modal-list-medecins',
  templateUrl: './modal-list-medecins.component.html',
  styleUrls: ['./modal-list-medecins.component.scss']
})
export class ModalListMedecinsComponent implements OnInit{

  constructor(public modalRef: MdbModalRef<ModalListMedecinsComponent>){}  

  ngOnInit(): void {

  }

}

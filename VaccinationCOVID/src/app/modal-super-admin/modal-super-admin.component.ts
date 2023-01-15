import { Component, Input, OnInit  } from '@angular/core';
import { Router } from '@angular/router';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';

@Component({
  selector: 'app-modal-super-admin',
  templateUrl: './modal-super-admin.component.html'
})
export class ModalSuperAdminComponent implements OnInit{
  constructor(public router: Router, public modalRef: MdbModalRef<ModalSuperAdminComponent>) {}
  ngOnInit(): void {
  }

  ajouterCentre(){
    this.router.navigate(['addCentre']);
  }
}

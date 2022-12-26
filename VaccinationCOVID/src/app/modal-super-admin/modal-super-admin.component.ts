import { Component, Input, OnInit  } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';

@Component({
  selector: 'app-modal-super-admin',
  templateUrl: './modal-super-admin.component.html'
})
export class ModalSuperAdminComponent implements OnInit{
  constructor(public modalRef: MdbModalRef<ModalSuperAdminComponent>) {}
  ngOnInit(): void {
  }
}

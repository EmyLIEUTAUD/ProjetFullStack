import { Component } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';

@Component({
  selector: 'app-modal-error-login',
  templateUrl: './modal-error-login.component.html',
  styleUrls: ['./modal-error-login.component.scss']
})
export class ModalErrorLoginComponent {

  constructor(public modalRef: MdbModalRef<ModalErrorLoginComponent>) {}

}

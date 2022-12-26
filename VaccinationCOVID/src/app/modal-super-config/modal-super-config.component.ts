import { Component, Input, OnInit  } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';

@Component({
  selector: 'app-modal-super-config',
  templateUrl: './modal-super-config.component.html'
})
export class ModalSuperConfigComponent implements OnInit{
  constructor(public modalRef: MdbModalRef<ModalSuperConfigComponent>) {}
  ngOnInit(): void {
  }
}
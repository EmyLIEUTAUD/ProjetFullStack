import { Component, OnInit, Input } from '@angular/core';
import { UserService } from '../_services/user.service';
import { ModalSuperAdminComponent} from '../modal-super-admin/modal-super-admin.component'
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { HttpClient} from '@angular/common/http';
import { ModalSuperConfigComponent } from '../modal-super-config/modal-super-config.component';

@Component({
  selector: 'app-board-superadmin',
  templateUrl: './board-superadmin.component.html',
  styleUrls: ['./board-superadmin.component.scss'],
  
})
export class BoardSuperadminComponent  implements OnInit  {
  modalRef: MdbModalRef<ModalSuperAdminComponent> | null = null;


  allSuperadmins;
  constructor(private modalService: MdbModalService) {}

  openModalSuperAdmin() {
    this.modalRef = this.modalService.open(ModalSuperAdminComponent)
  }
  openModalConfig() {
    this.modalRef = this.modalService.open(ModalSuperConfigComponent)
  }
  
  ngOnInit(): void{
     
  }

}
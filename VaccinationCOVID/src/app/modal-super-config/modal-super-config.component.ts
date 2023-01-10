import { Component, Input, OnInit  } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { HttpClient} from '@angular/common/http';
import { VaccinationAdminServiceService } from '../vaccination-admin-service.service';
import { Admin } from '../_models/admin';

@Component({
  selector: 'app-modal-super-config',
  templateUrl: './modal-super-config.component.html'
})
export class ModalSuperConfigComponent implements OnInit{
 admins: any=[];

  constructor(public modalRef: MdbModalRef<ModalSuperConfigComponent>,
    private http: HttpClient,
    private adminservice: VaccinationAdminServiceService) {}

  ngOnInit() {
    this.loadAdmins();
  }

  loadAdmins(){
    return this.adminservice.getAllVaccinationAdmin().subscribe((resultAdmins:{})=>{
      this.admins = resultAdmins;
    })   
  }

  deleteAdmin(id: any){
    if (window.confirm('Are you sure, you want to delete?')) {
      this.adminservice.deleteVaccinationAdmin(id).subscribe((data) => {
        this.loadAdmins();
      });
  }
}

}
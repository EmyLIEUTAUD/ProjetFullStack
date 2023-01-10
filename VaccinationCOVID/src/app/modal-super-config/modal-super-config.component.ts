import { Component, Input, OnInit  } from '@angular/core';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { HttpClient} from '@angular/common/http';
import { VaccinationAdminServiceService } from '../vaccination-admin-service.service';
import { Admin } from '../_models/admin';
import { ModalListMedecinsComponent } from '../modal-list-medecins/modal-list-medecins.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-modal-super-config',
  templateUrl: './modal-super-config.component.html'
})
export class ModalSuperConfigComponent implements OnInit{
 admins: any=[];
 selected?: Admin;

  constructor(public modalRef: MdbModalRef<ModalSuperConfigComponent>,
    private http: HttpClient,
    private adminservice: VaccinationAdminServiceService,
    public modalRefMedecins: MdbModalRef<ModalListMedecinsComponent> | null = null,
    private modalService: MdbModalService,
    private router : Router,
    ) {}

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
  editAdmin(admin : Admin){
    if(window.confirm('Are you sure, you want to update?')){
      this.selected = admin;
      this.router.navigate(['editAdmin',admin.id_admin]);
      }
    }


}
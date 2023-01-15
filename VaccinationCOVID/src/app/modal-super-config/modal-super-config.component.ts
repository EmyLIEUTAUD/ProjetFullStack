import { Component, OnInit  } from '@angular/core';
import { MdbModalRef } from 'mdb-angular-ui-kit/modal';
import { VaccinationAdminServiceService } from '../vaccination-admin-service.service';
import { Admin } from '../_models/admin';
import { Router } from '@angular/router';

@Component({
  selector: 'app-modal-super-config',
  templateUrl: './modal-super-config.component.html'
})
export class ModalSuperConfigComponent implements OnInit{
 admins: any=[];
 selected?: Admin;

  constructor(public modalRef: MdbModalRef<ModalSuperConfigComponent>,
    private adminservice: VaccinationAdminServiceService,
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
  editAdmin(adminSelected : Admin){
      this.selected = adminSelected;
      this.router.navigate(['editAdmin',adminSelected.id_admin]);
      
    }

    ajouterAdmin(){
      window.confirm('Pour ajouter un admin, il faut aller dans la modale Centres, chercher le centre dans lequel vous voulez ajouter un admin, puis cliquer sur person-icon.')
    }




}
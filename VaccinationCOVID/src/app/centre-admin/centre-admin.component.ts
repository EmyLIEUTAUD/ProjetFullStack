import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ChoixCentre } from '../choix-centre/choix-centre';
import { VaccinationCenterService } from '../vaccination-center.service';
import { HttpClient } from '@angular/common/http';
import { VaccinationAdminServiceService } from '../vaccination-admin-service.service';
import { Admin } from '../_models/admin';

@Component({
  selector: 'app-centre-admin',
  templateUrl: './centre-admin.component.html',
  styleUrls: ['./centre-admin.component.scss']
})
export class CentreAdminComponent implements OnInit{

  centre: ChoixCentre;
  admins: any=[];
  selected?: Admin;

  constructor(private route: ActivatedRoute, 
    private service: VaccinationCenterService, 
    private readonly http: HttpClient, 
    private readonly router: Router,
    private adminservice: VaccinationAdminServiceService,
    ) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => this.service.getVaccinationCenterById(params['gid']).subscribe(resultCenters=> {
      this.centre = resultCenters;
      console.log("test centre"+this.centre.gid);
    }));

    this.route.params.subscribe((params: Params) => this.adminservice.getVaccinationAdminByCentre(params['gid']).subscribe(resultAdmins=> {
      this.admins = resultAdmins;
    }));
  }

  

  deleteAdmin(id: any){
    if (window.confirm('Are you sure, you want to delete?')) {
      this.adminservice.deleteVaccinationAdmin(id).subscribe((data) => {
        this.reloadPage();
      });
    }
  }
  editAdmin(adminSelected : Admin){
      this.selected = adminSelected;
      //this.service.admin = adminSelected;
      this.router.navigate(['editAdmin',adminSelected.id_admin]);
      
    }

    ajouterAdmin(){
      this.router.navigate(['addAdmin']);
    }

    reloadPage(): void {
      window.location.reload();
    }
    
}

import { Component, OnInit } from '@angular/core';
import { VaccinationAdminServiceService } from '../vaccination-admin-service.service';
import { Admin } from '../_models/admin';
import { ActivatedRoute, Params, Router } from '@angular/router';

@Component({
  selector: 'app-change-admin',
  templateUrl: './change-admin.component.html',
  styleUrls: ['./change-admin.component.scss']
})
export class ChangeAdminComponent implements OnInit{

  admin : Admin;
  etag: Array<string> = [];

  constructor(
    private adminService: VaccinationAdminServiceService,
    private route: ActivatedRoute,
    private router : Router,){}

    ngOnInit(): void {
      this.route.params.subscribe((params: Params) => this.adminService.getVaccinationAdminById(params['idAdmin']).subscribe(resultadmin => {
        this.admin = resultadmin;
        console.log("test admin "+this.admin.id_admin);
      }));
    }

    onadminEdit(){
      console.log("Je veux éditer un admin")
      if(window.confirm('Are you sure, you want to update?')){
        this.adminService.editVaccinationAdmin(this.admin.id_admin, this.admin, this.etag).subscribe(data => {
          this.etag = [data.headers.get("ETag")];
          console.log(data.body);
          this.reloadPage();
        })
      }
    
    }
    reloadPage(): void {
      window.location.reload();
    }
}

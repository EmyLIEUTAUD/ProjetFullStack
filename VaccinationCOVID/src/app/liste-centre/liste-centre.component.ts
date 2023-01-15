import { Component, EventEmitter, Input, OnInit, Output, SimpleChange } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CentreChoisieService } from '../centre-choix-service.service';
import { ChoixCentre } from '../choix-centre/choix-centre';
import { ChoixVilleComponent } from '../choix-ville/choix-ville.component';
import { ChoixdelavilleService } from '../choixdelaville.service';
import { VaccinationCenterService } from '../vaccination-center.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-liste-centre',
  templateUrl: './liste-centre.component.html',
  styleUrls: ['./liste-centre.component.scss']
})
export class ListeCentreComponent implements OnInit {

  centers!: ChoixCentre[];
  selected?: ChoixCentre
  isLoggedIn = false;
  isLoginFailed = false;
  roles: string;
  isSuperAdmin = false;

  constructor(private router : Router,
    private service: VaccinationCenterService, 
    private service2: ChoixdelavilleService, 
    private service3: CentreChoisieService, 
    private route: ActivatedRoute,
    private tokenStorageService: TokenStorageService,) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();
    this.service2._nomVilleSubject.subscribe((nomVille) => {
      this.service.getAllVaccinationCenter(nomVille).subscribe(resultCenters=>{
        this.centers = resultCenters;
      });
    });
    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.authorities;
      this.isSuperAdmin = this.roles.includes('SUPER_ADMIN');
    }
  }

  isSpecialCenter(center: ChoixCentre){
    return center.comnom == "Nancy";
  }

  VilleSelectionnee(centercity: string){
    return this.service2.getNomVille()==centercity;
  }


  selectCenter(center: ChoixCentre){
    this.selected=center;
    this.service3.centre = center;
    console.log(center);
    this.router.navigate(['rdv',center.gid]);
  
  }
  modifierCenter(center: ChoixCentre){
    this.selected=center;
    this.service3.centre = center;
    console.log(center);
    this.router.navigate(['editCentre',center.gid]);
  }
  deleteCentre(center: ChoixCentre){
    if (window.confirm('Are you sure, you want to delete this center?')) {
      this.service.deleteVaccinationCentre(center.gid).subscribe((data) => {
        this.reloadPage();
      });
    }
  }

  centreAdmins(center: ChoixCentre){
    this.selected = center;
    this.router.navigate(['admins/centre/',center.gid])
  }

  onDeleted(center: ChoixCentre){
    delete this.selected;
    this.centers.splice(this.centers.indexOf(center),1);
  }
  reloadPage(): void {
    window.location.reload();
  }

}

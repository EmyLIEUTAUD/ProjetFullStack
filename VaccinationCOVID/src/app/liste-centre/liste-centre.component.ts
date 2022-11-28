import { Component, EventEmitter, Input, OnInit, Output, SimpleChange } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { CentreChoisieService } from '../centre-choix-service.service';
import { ChoixCentre } from '../choix-centre/choix-centre';
import { ChoixVilleComponent } from '../choix-ville/choix-ville.component';
import { ChoixdelavilleService } from '../choixdelaville.service';
import { VaccinationCenterService } from '../vaccination-center.service';

@Component({
  selector: 'app-liste-centre',
  templateUrl: './liste-centre.component.html',
  styleUrls: ['./liste-centre.component.scss']
})
export class ListeCentreComponent implements OnInit {

  centers!: ChoixCentre[];
  selected?: ChoixCentre

  constructor(private router : Router,private service: VaccinationCenterService, private service2: ChoixdelavilleService, private service3: CentreChoisieService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.service2._nomVilleSubject.subscribe((nomVille) => {
      this.service.getAllVaccinationCenter(nomVille).subscribe(resultCenters=>{
        this.centers = resultCenters;
      });
    })
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

  onDeleted(center: ChoixCentre){
    delete this.selected;
    this.centers.splice(this.centers.indexOf(center),1);
  }
}

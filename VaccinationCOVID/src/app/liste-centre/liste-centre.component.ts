import { Component, EventEmitter, Input, OnInit, Output, SimpleChange } from '@angular/core';
import { Observable } from 'rxjs';
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
  selected?: ChoixCentre;

  constructor(private service: VaccinationCenterService, private service2: ChoixdelavilleService) { }

  ngOnInit(): void {
    this.service.getAllVaccinationCenterFromCity(this.service2.getNomVille()).subscribe(resultCenters=>{
      this.centers = resultCenters;
    });
  }

  test(): Observable<ChoixCentre[]>{
    return this.service.getAllVaccinationCenter();
  }
  isSpecialCenter(center: ChoixCentre){
    return center.comnom == "Nancy";
  }

  VilleSelectionnee(centercity: string){
    return this.service2.getNomVille()==centercity;
  }

  selectCenter(center: ChoixCentre){
    this.selected=center;
  }

  onDeleted(center: ChoixCentre){
    delete this.selected;
    this.centers.splice(this.centers.indexOf(center),1);
  }
}

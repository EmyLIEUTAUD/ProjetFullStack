import { Component, OnInit } from '@angular/core';
import { ChoixCentre } from '../choix-centre/choix-centre';
import { VaccinationCenterService } from '../vaccination-center.service';

@Component({
  selector: 'app-liste-centre',
  templateUrl: './liste-centre.component.html',
  styleUrls: ['./liste-centre.component.scss']
})
export class ListeCentreComponent implements OnInit {

  
  centers!: ChoixCentre[];
  selected?: ChoixCentre;

  constructor(private service: VaccinationCenterService) { }

  ngOnInit(): void {
    this.centers = this.service.getAllVaccinationCenter();
  }

  isSpecialCenter(center: ChoixCentre){
    return center.city == "Nancy";
  }
  selectCenter(center: ChoixCentre){
    this.selected=center;
  }
  onDeleted(center: ChoixCentre){
    delete this.selected;
    this.centers.splice(this.centers.indexOf(center),1);
  }
}

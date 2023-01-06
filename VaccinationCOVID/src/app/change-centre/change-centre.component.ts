import { Component, OnInit, ViewChild  } from '@angular/core';
import { ChoixCentre } from '../choix-centre/choix-centre';
import { HttpClient } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import {VaccinationCenterService} from '../vaccination-center.service'
@Component({
  selector: 'app-change-centre',
  templateUrl: './change-centre.component.html',
  styleUrls: ['./change-centre.component.scss'],
  
})
export class ChangeCentreComponent implements OnInit{
centreData: any = {};
@ViewChild('centreForm') form: NgForm;
id = this.route.params['id'];
centre: ChoixCentre;

constructor(private http: HttpClient,
  public centreService: VaccinationCenterService,
  private route: ActivatedRoute,
  public router: Router){}

ngOnInit(): void {
  this.route.params.subscribe((params: Params) => this.centreService.getVaccinationCenterById(params['gid']).subscribe(resultCenters=> {
    this.centre = resultCenters;
    console.log("test centre"+this.centre.gid);
  }));

}
onCentreEdit(){

  

}


}

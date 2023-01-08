import { Component, OnInit, ViewChild  } from '@angular/core';
import { ChoixCentre } from '../choix-centre/choix-centre';
import { ActivatedRoute, Router, Params } from '@angular/router';
import {VaccinationCenterService} from '../vaccination-center.service'



@Component({
  selector: 'app-change-centre',
  templateUrl: './change-centre.component.html',
  styleUrls: ['./change-centre.component.scss'],
  
})
export class ChangeCentreComponent implements OnInit{


centre: ChoixCentre;

constructor(
  private centreService: VaccinationCenterService,
  private route: ActivatedRoute,
  private router : Router,){}

ngOnInit(): void {
  this.route.params.subscribe((params: Params) => this.centreService.getVaccinationCenterById(params['gid']).subscribe(resultCenters=> {
    this.centre = resultCenters;
    console.log("test centre"+this.centre.gid);
  }));

}
onCentreEdit(){

  if(window.confirm('Are you sure, you want to update?')){
    this.centreService.editVaccinationCentreById(this.centre.gid, this.centre).subscribe(data => {
      this.router.navigate(['/editCentre/:gid'])
    })
  }

}


}

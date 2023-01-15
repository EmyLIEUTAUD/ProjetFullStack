import { Component, OnInit } from '@angular/core';
import { ChoixCentre } from '../choix-centre/choix-centre';
import { ActivatedRoute, Params } from '@angular/router';
import {VaccinationCenterService} from '../vaccination-center.service'



@Component({
  selector: 'app-change-centre',
  templateUrl: './change-centre.component.html',
  styleUrls: ['./change-centre.component.scss'],
  
})
export class ChangeCentreComponent implements OnInit{


centre: ChoixCentre;
etag: Array<string> = [];

constructor(
  private centreService: VaccinationCenterService,
  private route: ActivatedRoute,){}

ngOnInit(): void {
  this.route.params.subscribe((params: Params) => this.centreService.getVaccinationCenterById(params['gid']).subscribe(resultCenters=> {
    this.centre = resultCenters;
  }));

}
onCentreEdit(){

  if(window.confirm('Are you sure, you want to update?')){
    this.centreService.editVaccinationCentreById(this.centre.gid, this.centre, this.etag).subscribe(data => {
      this.etag = [data.headers.get("ETag")];
      this.reloadPage();
    })
  }

}
reloadPage(): void {
  window.location.reload();
}


}

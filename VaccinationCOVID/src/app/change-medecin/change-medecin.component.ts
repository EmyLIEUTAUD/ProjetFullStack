import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ChoixMedecin } from '../choix-medecin/choix-medecin';
import { MedecinsService } from '../medecins.service';

@Component({
  selector: 'app-change-medecin',
  templateUrl: './change-medecin.component.html',
  styleUrls: ['./change-medecin.component.scss']
})
export class ChangeMedecinComponent implements OnInit {

  medecin: ChoixMedecin;
  etag: Array<string> = [];

  constructor(
    private medecinService: MedecinsService,
    private route: ActivatedRoute,
    private router : Router,){}
  
  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => this.medecinService.getMedecinById(params['idMedecin']).subscribe(resultMedecin => {
      this.medecin = resultMedecin;
    }));
  }

  onMedecinEdit(){
    if(window.confirm('Are you sure, you want to update?')){
      this.medecinService.editMedecinById(this.medecin.id_medecin, this.medecin, this.etag).subscribe(data => {
        this.etag = [data.headers.get("ETag")];
        this.reloadPage();
      })
    }
  
  }
  reloadPage(): void {
    window.location.reload();
  }

}

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
    console.log("Je veux changer un médecin");
    this.route.params.subscribe((params: Params) => this.medecinService.getMedecinById(params['idMedecin']).subscribe(resultMedecin => {
      this.medecin = resultMedecin;
      console.log("test medecin "+this.medecin.id_medecin);
    }));
  }

  onMedecinEdit(){
    console.log("Je veux éditer un médecin")
    if(window.confirm('Are you sure, you want to update?')){
      console.log("médecin modifié : "+this.medecin);
      this.medecinService.editMedecinById(this.medecin.id_medecin, this.medecin, this.etag).subscribe(data => {
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

import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ProfessionnelsService } from '../professionnels.service';
import { User } from '../_models/user';

@Component({
  selector: 'app-add-medecin',
  templateUrl: './add-medecin.component.html',
  styleUrls: ['./add-medecin.component.scss']
})
export class AddMedecinComponent implements OnInit {

  professionnels: any = [];
  etag: Array<string> = [];

  constructor(
    private professionnelService: ProfessionnelsService,
    private route: ActivatedRoute,
    private router : Router,){}
  
  ngOnInit(): void {
    this.loadProfessionnels();
  }

  loadProfessionnels(){
    return this.professionnelService.getAllProfessionnels().subscribe((resultProfessionnels:{})=>{
      this.professionnels = resultProfessionnels;
    })   
  }

  ajouterMedecin(professionnel: User){
    console.log("Je veux ajouter un médecin")
    if(window.confirm('Are you sure, you want to add?')){
      console.log("médecin ajouté : "+professionnel);
      this.professionnelService.addMedecinById(professionnel, this.etag).subscribe(data => {
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

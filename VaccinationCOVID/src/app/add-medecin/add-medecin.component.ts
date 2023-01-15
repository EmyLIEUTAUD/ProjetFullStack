import { Component, OnInit } from '@angular/core';
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
    private professionnelService: ProfessionnelsService){}
  
  ngOnInit(): void {
    this.loadProfessionnels();
  }

  loadProfessionnels(){
    return this.professionnelService.getAllProfessionnels().subscribe((resultProfessionnels:{})=>{
      this.professionnels = resultProfessionnels;
    })   
  }

  ajouterMedecin(professionnel: User){
    if(window.confirm('Are you sure, you want to add?')){
      this.professionnelService.addMedecinById(professionnel, this.etag).subscribe(data => {
        this.etag = [data.headers.get("ETag")];
        this.reloadPage();
      })
    }
  
  }
  reloadPage(): void {
    window.location.reload();
  }

}

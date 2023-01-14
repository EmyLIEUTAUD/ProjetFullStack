import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { ProfessionnelsService } from '../professionnels.service';
import { User } from '../_models/user';

@Component({
  selector: 'app-add-admin',
  templateUrl: './add-admin.component.html',
  styleUrls: ['./add-admin.component.scss']
})
export class AddAdminComponent {
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
  ajouterAdmin(professionnel: User){
    console.log("Je veux ajouter un admin")
    if(window.confirm('Are you sure, you want to add?')){
      console.log("admin ajouté : "+professionnel);
      this.route.params.subscribe((params: Params) =>this.professionnelService.addAdminById(professionnel, params['gid'],this.etag).subscribe(data => {
        this.etag = [data.headers.get("ETag")];
        console.log(data.body);
        this.reloadPage();
      }))
    }
  
  }
  reloadPage(): void {
    window.location.reload();
  }

}

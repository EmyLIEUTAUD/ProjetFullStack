import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IDeactivate } from '../i-deactivate';
import { VaccinationCenterService } from '../vaccination-center.service';

@Component({
  selector: 'app-add-centre',
  templateUrl: './add-centre.component.html',
  styleUrls: ['./add-centre.component.scss']
})
export class AddCentreComponent implements OnInit, IDeactivate{

  form: any = {
    nom:null,
    numAdresse:null,
    adresse:null,
    cp:null,
    comnom:null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor(private centreService: VaccinationCenterService){}

  ngOnInit(): void {
      
  }

  //Check if there any unsaved data etc. If yes then as for confirmation 
  canExit() : boolean {

    if (confirm("Voulez-vous vraiment quitter cette page ? Vos données seront perdues si le message de confirmation n'est pas affiché.")) {
      return true
    } else {
      return false
    }
  }

  onSubmit(): void{
    const {nom, numAdresse, adresse, cp, comnom} = this.form;

    this.centreService.saveCentre(nom, numAdresse, adresse, cp, comnom).then(() => {
      if(this.centreService.flag == true){
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      }
      else if(this.centreService.flag == false){
        console.log("J'ai flag == false");
        this.isSuccessful = false;
        this.isSignUpFailed = true;
      }
      console.log("isSignUpFailed : "+this.isSignUpFailed)
    });
  }

}

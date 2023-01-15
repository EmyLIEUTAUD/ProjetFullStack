import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IDeactivate } from '../i-deactivate';
import { SignUpEnvoiFormService } from '../sign-up-envoi-form.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit, IDeactivate {

  form: any = {
    nom:null,
    prenom:null,
    username:null,
    password:null
  };
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';

  constructor( private service: SignUpEnvoiFormService, private router: Router) { }

  ngOnInit(): void {
  }

  //Check if there any unsaved data etc. If yes then as for confirmation 
  canExit() : boolean {

    if (confirm("Voulez-vous vraiment quitter cette page ? Vos données seront perdues.")) {
      return true
    } else {
      return false
    }
  }

  onSubmit(): void{

    const {nom, prenom, username, pwd} = this.form;

    this.service.saveCompte(username, pwd, nom, prenom).then(() => {
      if(this.service.flag == true){
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      }
      else if(this.service.flag == false){
        this.isSuccessful = false;
        this.isSignUpFailed = true;
        this.errorMessage = "Mail déjà utilisé";
      }
    });

  }
}

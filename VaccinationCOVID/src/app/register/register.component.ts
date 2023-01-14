import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IDeactivate } from '../i-deactivate';
import { SignUpEnvoiFormService } from '../sign-up-envoi-form.service';
import { AuthenticationService } from '../_services/authentication.service';

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

  constructor(private authService: AuthenticationService, private service: SignUpEnvoiFormService, private router: Router) { }

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
    /*const {nom,prenom,username,password} = this.form;

    this.authService.register(nom,prenom,username,password).subscribe({
      next: (data) => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignupFailed = false;
      },
      error: (err) => {
        this.errorMessage = err.error.message;
        this.isSignupFailed = true;
      }
  });*/
    const {nom, prenom, username, pwd} = this.form;

    this.service.saveCompte(username, pwd, nom, prenom).then(() => {
      if(this.service.flag == true){
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      }
      else if(this.service.flag == false){
        console.log("J'ai flag == false");
        this.isSuccessful = false;
        this.isSignUpFailed = true;
        this.errorMessage = "Mail déjà utilisé";
      }
      //this.isSignUpFailed = this.service.isSignupFailed;
      //this.isSuccessful = this.service.isSuccessful;
      console.log("isSignUpFailed : "+this.isSignUpFailed)
    });

    //this.isSuccessful = true;

  }
}

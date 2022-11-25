import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  form: any = {
    nom:null,
    prenom:null,
    mail:null,
    pwd:null
  };
  isSuccessful = false;
  isSignupFailed = false;
  errorMessage = '';

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
  }

  onSubmit(): void{
    const {nom,prenom,mail,pwd} = this.form;

    this.authService.register(nom,prenom,mail,pwd).subscribe(
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignupFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignupFailed = true;
      }
    );
  }
}

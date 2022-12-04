import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../_services/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  form: any = {
    nom:null,
    prenom:null,
    username:null,
    password:null
  };
  isSuccessful = false;
  isSignupFailed = false;
  errorMessage = '';

  constructor(private authService: AuthenticationService) { }

  ngOnInit(): void {
  }

  onSubmit(): void{
    const {nom,prenom,username,password} = this.form;

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
  });
  }
}

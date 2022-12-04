import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthenticationService } from '../_services/authentication.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({  
    selector: 'app-login',
    templateUrl:'./login.component.html' })
export class LoginComponent implements OnInit {
    
    error = '';
    isLoggedIn = false;
    isLoginFailed = false;
    roles: string[] = [];
    errorMessage = '';

    form: any = {
        username: null,
        password: null
      };
    constructor(
      private router: Router,
        private authenticationService: AuthenticationService,
        private tokenStorage: TokenStorageService
    ) {}


    ngOnInit(): void {
        if (this.tokenStorage.getToken()) {
          this.isLoggedIn = true;
          this.roles = this.tokenStorage.getUser().scopes;
          this.router.navigate([''])

        }
      }
  
    onSubmit(): void {
        const { username, password } = this.form;
    
        this.authenticationService.login(username, password).subscribe({
          next : (data) => {
            this.tokenStorage.saveToken(data.accessToken);
            this.tokenStorage.saveUser(data);
    
            this.isLoginFailed = false;
            this.isLoggedIn = true;
            this.roles = this.tokenStorage.getUser().scopes;
            this.reloadPage();
          },
          error : (err) => {
            this.errorMessage = err.error.message;
            this.isLoginFailed = true;
          }
    });
      }
      reloadPage(): void {
        window.location.reload();
      }
}

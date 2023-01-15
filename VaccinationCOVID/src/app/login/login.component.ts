import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
          this.roles = this.tokenStorage.getUser().authorities;
          this.router.navigate([''])
          
        }
      }
  
    onSubmit(): void {
        const { username, password } = this.form;
    
        this.authenticationService.login(username, password).subscribe({
          next : (data) => {
            this.tokenStorage.saveToken(data);
            this.tokenStorage.saveUser(data);
            this.tokenStorage.saveAuthToken(data.body.token);
            this.roles = this.tokenStorage.getUser().authorities;

            this.isLoginFailed = false;
            this.isLoggedIn = true;
            this.reloadPage();
            
          },
          error : (err) => {
            this.isLoginFailed = true;
            this.errorMessage = err.error.message;
          }
    })
      }
      reloadPage(): void {
        window.location.reload();
      }
}

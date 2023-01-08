import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from './_services/token-storage.service';
import { HttpClient } from '@angular/common/http';

import { AuthenticationService } from './_services/authentication.service';
import { User } from './_models/user';


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  
  private roles: string;
  isLoggedIn = false;
  showMedecinBoard = false;
  showAdminBoard = false;
  showSuperAdminBoard = false;
  username?: string;
title = 'VaccinationCOVID';

  constructor(private tokenStorageService: TokenStorageService, private readonly http: HttpClient, private readonly router: Router) { }

  word = '';
  infos = '';

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.authorities;

      
      this.showSuperAdminBoard = this.roles.includes('SUPER_ADMIN');
      this.showAdminBoard = this.roles.includes('ADMIN');
  
      this.showMedecinBoard = this.roles.includes('MEDECIN');

      this.username = user.sub;
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

}



import { Component } from '@angular/core';
import { TokenStorageService } from './_services/token-storage.service';


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
  showProfessionnalBoard = false;
  username?: string;
title = 'VaccinationCOVID';

  constructor(private tokenStorageService: TokenStorageService) { }

  word = '';
  infos = '';

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.authorities;

      
      this.showSuperAdminBoard = this.roles.includes('SUPER_ADMIN');
      this.showAdminBoard = this.roles.includes('ADMIN');
      if(this.roles.length == 0){
        this.showProfessionnalBoard = true;
      }
      this.showMedecinBoard = this.roles.includes('MEDECIN');

      this.username = user.sub;
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }

}



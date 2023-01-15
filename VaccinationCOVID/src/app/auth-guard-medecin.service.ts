import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AuthenticationService } from './_services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardMedecinService {

  constructor(private router: Router, private authenticationService: AuthenticationService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean|UrlTree {
    
    if (!this.authenticationService.isMedecinLoggedIn()) {
      alert('You are not allowed to view this page. You are redirected to login Page');
      
      this.router.navigate(["/login"]);
      return false;
    } 
    
  return true;
  }

}

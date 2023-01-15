import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { AuthenticationService } from './_services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardAdminService implements CanActivate {

  constructor(private router: Router, private authenticationService: AuthenticationService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean|UrlTree {
    
    if (!this.authenticationService.isAdminLoggedIn()) {
      alert('You are not allowed to view this page. You are redirected to login Page');
      
      this.router.navigate(["/login"]);
      return false;
    } 
    
  return true;
  }

}

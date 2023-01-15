import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, CanDeactivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { IDeactivate } from './i-deactivate';
import { AuthenticationService } from './_services/authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate, CanDeactivate<IDeactivate>{

  component: Object;
  route: ActivatedRouteSnapshot;

  constructor(private router: Router, private authenticationService: AuthenticationService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean|UrlTree {
    
    if (!this.authenticationService.isUserLoggedIn()) {
      alert('You are not allowed to view this page. You are redirected to login Page');
      
      this.router.navigate(["/login"],{ queryParams: { retUrl: route.url} });
      return false;
    } 
    
  return true;
  }

  canDeactivate(component:IDeactivate, route: ActivatedRouteSnapshot, state: RouterStateSnapshot, nextState: RouterStateSnapshot) : Observable<boolean> | Promise<boolean> | boolean {
    
    return component.canExit ? component.canExit() : true;
  }
    
}

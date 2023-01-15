import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { BehaviorSubject, Observable,  } from 'rxjs';
import { User } from '../_models/user';
import { TokenStorageService } from './token-storage.service';

const HEADERS = new HttpHeaders({ 'Content-Type': 'application/json' });


@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;
    token: string;

    constructor(private http: HttpClient, 
      private tokenStorageService: TokenStorageService,) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    
    login(username: string, password: string) : Observable<any> {
        return this.http.post('http://localhost:8080/login/authenticate', {
          username,
          password
        }, {observe: "response", headers : HEADERS});
      }

    isUserLoggedIn(): boolean {
      if(this.tokenStorageService.getAuthToken() != null ){
        if(this.tokenStorageService.isTokenExpired(this.tokenStorageService.getAuthToken())){
          return true;
        }
      }
      return false;
    }

    isProfessionnelLoggedIn(): boolean {
      if(this.tokenStorageService.getAuthToken() != null ){
        if(this.tokenStorageService.isTokenExpired(this.tokenStorageService.getAuthToken())){
          if(this.tokenStorageService.getUser().authorities == ""){
            return true;
          }
        }
      }
      return false;
    }

    isMedecinLoggedIn(): boolean {
      if(this.tokenStorageService.getAuthToken() != null ){
        if(this.tokenStorageService.isTokenExpired(this.tokenStorageService.getAuthToken())){
          if(this.tokenStorageService.getUser().authorities == "MEDECIN"){
            return true;
          }
        }
      }
      return false;
    }

    isAdminLoggedIn(): boolean {
      if(this.tokenStorageService.getAuthToken() != null ){
        if(this.tokenStorageService.isTokenExpired(this.tokenStorageService.getAuthToken())){
          if(this.tokenStorageService.getUser().authorities == "ADMIN"){
            return true;
          }
        }
      }
      return false;
    }

    isSuperAdminLoggedIn(): boolean {
      if(this.tokenStorageService.getAuthToken() != null ){
        if(this.tokenStorageService.isTokenExpired(this.tokenStorageService.getAuthToken())){
          if(this.tokenStorageService.getUser().authorities == "SUPER_ADMIN"){
            return true;
          }
        }
      }
      return false;
    }

    register(nom : string, prenom: string,username: string , password: string): Observable<any> {
        return this.http.post('http://localhost:8080/login/nouveau', {
          nom,
          prenom,
          username,
          password
        }, {observe: "response", headers: HEADERS});
      }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }
}

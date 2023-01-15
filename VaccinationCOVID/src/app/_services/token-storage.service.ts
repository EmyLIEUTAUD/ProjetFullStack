import { Injectable } from '@angular/core';
import * as jwt_decode from 'jwt-decode';

const TOKEN_KEY = 'token';
const USER_KEY = 'auth-user';
const AUTHTOKEN_KEY = 'authtoken';
@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {
 


  constructor() { }

  
 
  
  public getAuthToken():string {
    return JSON.parse(sessionStorage.getItem(AUTHTOKEN_KEY));
    }

  public saveAuthToken(token: any): void{
      window.sessionStorage.removeItem(AUTHTOKEN_KEY);
    window.sessionStorage.setItem(AUTHTOKEN_KEY, JSON.stringify(token));
    }

  signOut(): void {
    window.sessionStorage.clear();
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public saveUser(user: any): void {
    window.sessionStorage.removeItem(USER_KEY);
    window.sessionStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  public isTokenExpired(token: string) {
  const expiry = (JSON.parse(window.atob(token.split('.')[1]))).exp;
  console.log("exp token : "+expiry);
  return expiry * 1000 > Date.now();
}
 

  public getUser(): any {
    const user = window.sessionStorage.getItem(USER_KEY);
    if (user) {
      return JSON.parse(window.atob(user.split('.')[1]));
    }

    return {};
    

  }
}
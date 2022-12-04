import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders  } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { User } from '../_models/user';
const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  
@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;

    constructor(private http: HttpClient) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUser')));
        this.currentUser = this.currentUserSubject.asObservable();
    }

    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }

    
    login(username: string, password: string): Observable<any> {
        return this.http.post('http://localhost:8080/login/authenticate', {
          username,
          password
        }, httpOptions);
      }

    register(nom : string, prenom: string,username: string , password: string): Observable<any> {
        return this.http.post('http://localhost:8080/login/nouveau', {
          nom,
          prenom,
          username,
          password
        },httpOptions);
      }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
        this.currentUserSubject.next(null);
    }
}

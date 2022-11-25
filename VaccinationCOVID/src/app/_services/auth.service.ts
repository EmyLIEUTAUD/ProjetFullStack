import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

const AUTH_API = 'http://localhost:8080/login/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  
  
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient) { }

  login(mail: string, mdp: string): Observable<any>{
    return this.httpClient.post(AUTH_API+'authenticate',{
      mail,
      mdp
    },httpOptions);
  }

  register(nom: string, prenom: string, mail: string, mdp: string): Observable<any>{
    return this.httpClient.post(AUTH_API+'nouveau',{
      nom,
      prenom,
      mail,
      mdp
    },httpOptions);
  }
}

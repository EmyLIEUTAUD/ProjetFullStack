import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

const API_URL = 'http://localhost:8080/'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  getAdminBoard(): Observable<any>{
    return this.http.get(API_URL + 'admin',{responseType: 'text'});
  }

  getMedecinBoard(): Observable<any>{
    return this.http.get(API_URL + '/admin/medecins',{responseType: 'text'});
  }

  getModeratorBoard(): Observable<any>{
    return this.http.get(API_URL + 'mod',{responseType: 'text'});
  }



}

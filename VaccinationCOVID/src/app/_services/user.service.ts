import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const API_URL = 'http://localhost:8080/api/test/';



@Injectable({ providedIn: 'root' })
export class UserService {
    private usersUrl: string;
    constructor(private http: HttpClient) {
      
     }
     getPublicContent(): Observable<any> {
        return this.http.get(API_URL + 'all', { responseType: 'text' });
      }
    
      getMedecinBoard(): Observable<any> {
        return this.http.get(API_URL + 'medecin', { responseType: 'text' });
      }
    
      getAdminBoard(): Observable<any> {
        return this.http.get(API_URL + 'admin', { responseType: 'text' });
      }
    
      getsueprAdminBoard(): Observable<any> {
        return this.http.get(API_URL + 'superAdmin', { responseType: 'text' });
      }
    
    
}
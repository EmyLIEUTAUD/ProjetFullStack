import { Injectable } from '@angular/core';
import { HttpHeaders  } from '@angular/common/http';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Admin } from './_models/admin';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class VaccinationAdminServiceService {

  constructor(private  httpClient: HttpClient) { }

  getAllVaccinationAdmin() : Observable<Admin>{
    const headers = new HttpHeaders({'Content-Type': 'application/json'})
    return this.httpClient.get<Admin>("http://localhost:8080/admin/administrateurs",{headers: headers});
  }

  deleteVaccinationAdmin(id: any){
    return this.httpClient.delete<Admin>("http://localhost:8080/admin/administrateurs/supprimer/"+id);
  }
}

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
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    return this.httpClient.get<Admin>("http://localhost:8080/admin/administrateurs",{headers: headers});
  }

  getVaccinationAdminById(id: number) : Observable<Admin>{
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})

    return this.httpClient.get<Admin>("http://localhost:8080/admin/administrateurs/id/"+id, {headers: headers});
  }

  getVaccinationAdminByPersonneIdentifiant(id: number) : Observable<Admin>{
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})

    return this.httpClient.get<Admin>("http://localhost:8080/admin/administrateurs/idPersonne/"+id, {headers: headers});
  }

  getVaccinationAdminByCentre(gid: number) : Observable<Admin>{
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})

    return this.httpClient.get<Admin>("http://localhost:8080/admin/administrateurs/centre/"+gid, {headers: headers});
  }

  deleteVaccinationAdmin(id: any){
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    return this.httpClient.delete<Admin>("http://localhost:8080/admin/administrateurs/supprimer/"+id);
  }

  editVaccinationAdmin(id: number,editAdmin: any, etag: Array<string>): Observable<HttpResponse<Admin>>{
    let headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache','If-Match': etag})
    return this.httpClient.put<Admin>("http://localhost:8080/admin/administrateurs/modifier/"+id, JSON.stringify(editAdmin), {observe: "response", headers: headers});
  }

}

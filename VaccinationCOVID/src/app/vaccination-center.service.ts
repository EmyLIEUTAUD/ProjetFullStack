import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { ChoixCentre } from './choix-centre/choix-centre';
import { HttpHeaders  } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class VaccinationCenterService {

  isSuccessful = false;
  isSignupFailed = false;
  errorMessage = '';
  etag: Array<string> = [];
  flag: boolean = false;

/*CENTERS: ChoixCentre[] = [
    {gid:1, name:"hopital central", city: "nancy", numAdresse: "2 rue", adresse: "3", cp: 5, horairesLundi: "a", horairesMardi: "a", horairesMercredi: "a", horairesJeudi: "a", horairesVendredi: "a", horairesSamedi: "a", horairesDimanche: "a"},
    {gid:2, name:"truc central", city: "nancy", numAdresse: "2 rue", adresse: "3", cp: 5, horairesLundi: "a", horairesMardi: "a", horairesMercredi: "a", horairesJeudi: "a", horairesVendredi: "a", horairesSamedi: "a", horairesDimanche: "a"},
    {gid:3, name:"machin central", city: "paris", numAdresse: "2 rue", adresse: "4", cp: 5, horairesLundi: "a", horairesMardi: "a", horairesMercredi: "a", horairesJeudi: "a", horairesVendredi: "a", horairesSamedi: "a", horairesDimanche: "a"},
    {gid:4, name:"chose central", city: "paris", numAdresse: "2 rue", adresse: "5", cp: 5, horairesLundi: "a", horairesMardi: "a", horairesMercredi: "a", horairesJeudi: "a", horairesVendredi: "a", horairesSamedi: "a", horairesDimanche: "a"}
  ];*/

  constructor(private  httpClient: HttpClient) { }

  getAllVaccinationCenter(comnom: string) : Observable<ChoixCentre[]>{
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    return this.httpClient.get<ChoixCentre[]>("http://localhost:8080/public/centres/"+comnom, {headers: headers});
  }
  getVaccinationCenterById(gid: number) : Observable<ChoixCentre>{
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    return this.httpClient.get<ChoixCentre>("http://localhost:8080/public/centres/id/"+gid, {headers: headers});
  }
  editVaccinationCentreById(gid: number, editCentre: any, etag: Array<string>): Observable<HttpResponse<ChoixCentre>>{
    let headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache', 'If-Match': etag});

    return this.httpClient.put<ChoixCentre>("http://localhost:8080/admin/centres/modifier/"+gid, JSON.stringify(editCentre), {observe: "response", headers: headers});
  }
  deleteVaccinationCentre(id: any){
    const headers = new HttpHeaders({'Content-Type': 'application/json', 'Cache-Control': 'no-cache'})
    return this.httpClient.delete<ChoixCentre>("http://localhost:8080/admin/centres/supprimer/"+id, {headers: headers});
  }
  async saveCentre(nom: string, numAdresse: string, adresse: string, cp: number, comnom: string): Promise<void>{
    const centre = {
      nom: nom,
      numAdresse: numAdresse,
      adresse: adresse,
      cp: cp,
      comnom: comnom
    };

    let ifMatch = new HttpHeaders({'Cache-Control': 'no-cache', "If-Match": this.etag})
    let flag2;
    let flagPromise: Promise<void> = new Promise((flag => flag2 = flag));
    this.httpClient.post<string>("http://localhost:8080/admin/centres/nouveau",centre, {observe: "response", headers: ifMatch}).subscribe({
      next: (data) => {
        console.log("succès");
        console.log("data body : "+data.body);
        this.isSuccessful = true;
        this.isSignupFailed = false;
        this.etag = [data.headers.get("ETag")];
        this.flag = true;
        flag2(this.flag)
      },
      error: (err) => {
        this.errorMessage = err.error.message;
        this.isSignupFailed = true;
        this.isSuccessful = false;
        this.flag = false;
        flag2(this.flag)
      }
  });/*
      data => {
         console.log("succès")
         console.log(data)
      }
   );*/
   return flagPromise;
  }

}
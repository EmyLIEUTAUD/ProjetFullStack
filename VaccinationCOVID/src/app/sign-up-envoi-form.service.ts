import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SignUpEnvoiFormService {

  isSuccessful = false;
  isSignupFailed = false;
  errorMessage = '';
  etag: Array<string> = [];
  flag: boolean = false;

  constructor(private  httpClient: HttpClient) { }

  async saveCompte(username: string, password: string, nom: string, prenom: string): Promise<void>{
    const compte = {
      username: username,
      password: password,
      nom: nom,
      prenom: prenom
    };

    let ifMatch = new HttpHeaders({'Cache-Control': 'no-cache', "If-Match": this.etag})
    let flag2;
    let flagPromise: Promise<void> = new Promise((flag => flag2 = flag));
    this.httpClient.post<string>("http://localhost:8080/login/nouveau",compte, {observe: "response", headers: ifMatch}).subscribe({
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

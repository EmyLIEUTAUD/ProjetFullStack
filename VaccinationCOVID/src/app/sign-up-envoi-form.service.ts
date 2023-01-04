import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class SignUpEnvoiFormService {

  isSuccessful = false;
  isSignupFailed = false;
  errorMessage = '';

  constructor(private  httpClient: HttpClient) { }

  saveCompte(username: string, password: string, nom: string, prenom: string){
    const compte = {
      username: username,
      password: password,
      nom: nom,
      prenom: prenom
    };

    this.httpClient.post<string>("login/nouveau",compte).subscribe({
      next: (data) => {
        console.log("succès");
        console.log(data);
        this.isSuccessful = true;
        this.isSignupFailed = false;
      },
      error: (err) => {
        this.errorMessage = err.error.message;
        this.isSignupFailed = true;
      }
  });/*
      data => {
         console.log("succès")
         console.log(data)
      }
   );*/
  }

}

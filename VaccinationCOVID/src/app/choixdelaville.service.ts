import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ChoixdelavilleService {

  nomVille: string;

  constructor() { }

  setNomVille(nomVille: string){
    this.nomVille=nomVille;
  }
  getNomVille() : string{
    return this.nomVille;
  }
}

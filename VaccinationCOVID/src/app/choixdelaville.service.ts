import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ChoixdelavilleService {

  _nomVilleSubject: Subject<string> = new Subject<string>(); // utiliser comme file d'attente pour actualiser nomVille

  nomVille: string;

  constructor() { }

  setNomVille(nomVille: string){
    this.nomVille=nomVille;
    this._nomVilleSubject.next(nomVille); // ajoute nomVille dans la liste d'attente
  }
  getNomVille() : string{
    return this.nomVille;
  }
}

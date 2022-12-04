import { Injectable } from '@angular/core';
import { ChoixCentre } from './choix-centre/choix-centre';

@Injectable({
  providedIn: 'root'
})
export class CentreChoisieService {

  centre: ChoixCentre;
  constructor() { }

  setCentreChoisie(centre: ChoixCentre){
    this.centre=centre;
  }
  getCentreChoisie() : ChoixCentre{
    return this.centre;
  }
}
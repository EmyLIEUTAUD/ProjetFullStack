import { Component, OnInit } from '@angular/core';
import { ChoixdelavilleService } from '../choixdelaville.service';

@Component({
  selector: 'app-choix-ville',
  templateUrl: './choix-ville.component.html',
  styleUrls: ['./choix-ville.component.scss']
})
export class ChoixVilleComponent implements OnInit {
  nomVille : string;

  constructor(private service: ChoixdelavilleService) { }

  ngOnInit(): void {
  }
  
  setVille(nomVille: string){
    this.service.setNomVille(nomVille);
  }

}

import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ChoixMedecin } from './choix-medecin';

@Component({
  selector: 'app-choix-medecin',
  templateUrl: './choix-medecin.component.html',
  styleUrls: ['./choix-medecin.component.scss']
})
export class ChoixMedecinComponent implements OnInit {

  @Input() center?: ChoixMedecin;

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id_medecin'));
  }

}

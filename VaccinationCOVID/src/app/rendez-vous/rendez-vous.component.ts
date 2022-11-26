import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { ChoixCentre } from '../choix-centre/choix-centre';
import { VaccinationCenterService } from '../vaccination-center.service';

@Component({
  selector: 'app-rendez-vous',
  templateUrl: './rendez-vous.component.html',
  styleUrls: ['./rendez-vous.component.scss']
})
export class RendezVousComponent implements OnInit {
  
  centre: ChoixCentre;
  constructor(private route: ActivatedRoute, private service: VaccinationCenterService) { }
  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => this.centre = this.service.getVaccinationCenterById(params['gid']));
  }
}
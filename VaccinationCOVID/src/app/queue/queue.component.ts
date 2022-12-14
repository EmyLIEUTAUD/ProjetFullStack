import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { interval, Observable, Subscription, timer } from 'rxjs';
import { EnvoiFormulaireService } from '../envoi-formulaire.service';
import { GeneralHttpInterceptorService } from '../general-http-interceptor.service';
import { VaccinationCenterService } from '../vaccination-center.service';

@Component({
  selector: 'app-queue',
  templateUrl: './queue.component.html',
  styleUrls: ['./queue.component.scss']
})
export class QueueComponent implements OnInit, OnDestroy {

  infos = '';
  temps = 0;
  difference = 0;
  tempsRestant = 0;

  subscription: Subscription;

  constructor(private route: ActivatedRoute, private service: VaccinationCenterService,private service2: EnvoiFormulaireService, private readonly router: Router, private interceptor: GeneralHttpInterceptorService) { 
    this.temps = this.route.snapshot.params['temps'];
    console.log("param : ", this.temps);
  }  

  ngOnInit(): void {
    this.subscription = interval(1000)
           .subscribe(x => { this.updateTime(); });
  }

  ngOnDestroy() {
    this.subscription.unsubscribe();
    this.router.navigateByUrl("/home");
 }

  updateTime(){
    this.tempsRestant = this.temps - this.difference;
    this.difference = this.difference + 1;
    console.log("temps restant = ", this.tempsRestant);
    if(this.tempsRestant == 0){
      this.ngOnDestroy();
    }
  }

}

import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { interval, Subscription } from 'rxjs';

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

  constructor(private route: ActivatedRoute,  private readonly router: Router) { 
    this.temps = this.route.snapshot.params['temps'];
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
    if(this.tempsRestant == 0){
      this.ngOnDestroy();
    }
  }

}

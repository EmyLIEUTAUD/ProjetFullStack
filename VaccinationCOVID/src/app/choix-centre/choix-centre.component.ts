import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ChoixCentre } from './choix-centre';

@Component({
  selector: 'app-choix-centre',
  templateUrl: './choix-centre.component.html',
  styleUrls: ['./choix-centre.component.scss']
})
export class ChoixCentreComponent implements OnInit {

  @Input() center?: ChoixCentre;
  @Output() deleted = new EventEmitter<ChoixCentre>();

  constructor(private route: ActivatedRoute) { }

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
  }

  isNameNotEmpty(){
    return this.center.name.length>0;
  }
  delete(){
    this.deleted.emit(this.center);
  }
}

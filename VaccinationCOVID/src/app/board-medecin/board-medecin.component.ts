import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-board-medecin',
  templateUrl: './board-medecin.component.html',
  styleUrls: ['./board-medecin.component.scss']
})
export class BoardMedecinComponent implements OnInit {
  content?: string;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getMedecinBoard().subscribe({
      next :(data) => {
        this.content = data;
      },
      error: err  => {
        this.content = JSON.parse(err.error).message;
      }
  });
  }
  

}

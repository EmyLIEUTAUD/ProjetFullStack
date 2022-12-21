import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  content?: string;
  title = 'Rendez-vous vaccination COVID';

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getPublicContent().subscribe({
      next :(data) => {
        this.content = data;
      },
      error: err => {
        this.content = JSON.parse(err.error).message;
      }
  });
  }
}
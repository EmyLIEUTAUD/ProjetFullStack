import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-board-superadmin',
  templateUrl: './board-superadmin.component.html',
  styleUrls: ['./board-superadmin.component.scss']
})
export class BoardSuperadminComponent implements OnInit {
  content?: string;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getsueprAdminBoard().subscribe({
      next : (data) => {
        this.content = data;
      },
      error : err => {
        this.content = JSON.parse(err.error).message;
      }
  });
  }

}

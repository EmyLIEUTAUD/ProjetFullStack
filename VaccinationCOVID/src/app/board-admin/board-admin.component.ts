import { Component, OnInit } from '@angular/core';
import { first } from 'rxjs/operators';

import { User } from '../_models/user';
import { UserService } from '../_services/user.service';

@Component({  
    selector: 'app-board-admin',
    templateUrl: './board-admin.component.html' })
export class BoardAdminComponent implements OnInit {
    content?: string;

    constructor(private userService: UserService) { }
  
    ngOnInit(): void {
      this.userService.getAdminBoard().subscribe({
        next :(data) => {
          this.content = data;
        },
        error: err  => {
          this.content = JSON.parse(err.error).message;
        }
    });
    }
    
}

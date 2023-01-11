import { Injectable } from '@angular/core';
import { Admin } from './_models/admin';

@Injectable({
  providedIn: 'root'
})
export class AdminChoixServiceService {

  admin: Admin;
  constructor() { }

  setAdminChoisie(admin: Admin){
    this.admin=admin;
  }
  getAdminChoisie() : Admin{
    return this.admin;
  }
}

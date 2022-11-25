import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ChoixCentreComponent } from './choix-centre/choix-centre.component';
import { ListeCentreComponent } from './liste-centre/liste-centre.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { BoardMedecinComponent } from './board-medecin/board-medecin.component';

const routes: Routes = [
  {path: "public", component: ListeCentreComponent},
  {path: "public/detail/:id", component: ChoixCentreComponent},
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'medecin', component: BoardMedecinComponent},
  {path: 'mdo', component: BoardModeratorComponent},
  {path: 'admin', component: BoardAdminComponent},

  //{path: '', redirectTo: '/public', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

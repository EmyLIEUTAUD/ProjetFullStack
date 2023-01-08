import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ChoixCentreComponent } from './choix-centre/choix-centre.component';
import { ListeCentreComponent } from './liste-centre/liste-centre.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardMedecinComponent } from './board-medecin/board-medecin.component';
import { BoardSuperadminComponent } from './board-superadmin/board-superadmin.component';
import { ChoixVilleComponent } from './choix-ville/choix-ville.component';
import { RendezVousComponent } from './rendez-vous/rendez-vous.component';
import {ChangeCentreComponent} from './change-centre/change-centre.component';
const routes: Routes = [

  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent },
  {path: 'register', component: RegisterComponent},
  {path: 'profile', component: ProfileComponent},
  {path: 'admin', component: BoardAdminComponent},
  {path: 'medecin', component: BoardMedecinComponent},
  {path: 'superAdmin', component: BoardSuperadminComponent},
  {path: '', redirectTo: 'home',pathMatch: 'full'  },
  {path: "villes", component: ChoixVilleComponent},
  {path: "rdv/:gid", title: 'Page de rendez-vous', component: RendezVousComponent},
  {path: '', redirectTo: '/public', pathMatch: 'full'},
  {path: "public",title: 'Accueil', component: ChoixVilleComponent},
  {path: "rdv/:gid", title: 'Inscription', component: RendezVousComponent},
  {path: '', redirectTo: '/public', pathMatch: 'full'},
  {path: 'editCentre/:gid', component: ChangeCentreComponent}

  //{path: '', redirectTo: '/public', pathMatch: 'full'},



  //{path: '**', title: 'Page erreur', component: PageNotFoundComponent }, //renvoie à une page d'erreur si pas bon url

]

// export const AppRoutingModule = RouterModule.forRoot(routes);

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

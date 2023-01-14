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
import { QueueComponent } from './queue/queue.component';
import { BoardProfessionnelComponent } from './board-professionnel/board-professionnel.component';
import { ChangeMedecinComponent } from './change-medecin/change-medecin.component';
import { ChangeAdminComponent } from './change-admin/change-admin.component';
import { AddMedecinComponent } from './add-medecin/add-medecin.component';
import { CentreAdminComponent } from './centre-admin/centre-admin.component';
import { AddAdminComponent } from './add-admin/add-admin.component';
import { AuthGuardService } from './auth-guard.service';
const routes: Routes = [

  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent },
  {path: 'register', component: RegisterComponent, canDeactivate: [AuthGuardService]},
  {path: 'profile', component: ProfileComponent, canActivate: [AuthGuardService]},
  {path: 'admin', component: BoardAdminComponent, canActivate: [AuthGuardService]},
  {path: 'medecin', component: BoardMedecinComponent, canActivate: [AuthGuardService]},
  {path: 'superAdmin', component: BoardSuperadminComponent, canActivate: [AuthGuardService]},
  {path: 'professionnel', component: BoardProfessionnelComponent, canActivate: [AuthGuardService]},
  {path: '', redirectTo: 'home',pathMatch: 'full'  },
  {path: "villes", component: ChoixVilleComponent},
  {path: "rdv/:gid", title: 'Page de rendez-vous', component: RendezVousComponent,canDeactivate: [AuthGuardService]},
  {path: '', redirectTo: '/public', pathMatch: 'full'},
  {path: "public",title: 'Accueil', component: ChoixVilleComponent},
  {path: "rdv/:gid", title: 'Inscription', component: RendezVousComponent, canDeactivate: [AuthGuardService]},
  {path: '', redirectTo: '/public', pathMatch: 'full'},
  {path: 'editCentre/:gid', component: ChangeCentreComponent, canActivate: [AuthGuardService]},
  {path: 'queue/:temps', component: QueueComponent},
  {path: 'editMedecin/:idMedecin', component: ChangeMedecinComponent, canActivate: [AuthGuardService]},
  {path: 'editAdmin/:idAdmin', component: ChangeAdminComponent, canActivate: [AuthGuardService]},
  {path: 'addMedecin', component: AddMedecinComponent, canActivate: [AuthGuardService]},
  {path: 'admins/centre/:gid', component: CentreAdminComponent, canActivate: [AuthGuardService]},
  {path: 'addAdmin/:gid', component: AddAdminComponent, canActivate: [AuthGuardService]},

  //{path: '', redirectTo: '/public', pathMatch: 'full'},



  //{path: '**', title: 'Page erreur', component: PageNotFoundComponent }, //renvoie à une page d'erreur si pas bon url

]

// export const AppRoutingModule = RouterModule.forRoot(routes);

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

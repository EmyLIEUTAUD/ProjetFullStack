import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ChoixCentreComponent } from './choix-centre/choix-centre.component';
import { ChoixVilleComponent } from './choix-ville/choix-ville.component';
import { RendezVousComponent } from './rendez-vous/rendez-vous.component';

const routes: Routes = [
  {path: "public",title: 'Page de recherche', component: ChoixVilleComponent},
  {path: "rdv/:gid", title: 'Page de rendez-vous', component: RendezVousComponent},
  {path: '', redirectTo: '/public', pathMatch: 'full'},
  //{path: '**', title: 'Page erreur', component: PageNotFoundComponent }, //renvoie à une page d'erreur si pas bon url
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

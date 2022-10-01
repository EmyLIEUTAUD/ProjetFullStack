import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ChoixCentreComponent } from './choix-centre/choix-centre.component';
import { ListeCentreComponent } from './liste-centre/liste-centre.component';

const routes: Routes = [
  {path: "centers", component: ListeCentreComponent},
  {path: "centers/detail/:id", component: ChoixCentreComponent},
  {path: '', redirectTo: '/centers', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

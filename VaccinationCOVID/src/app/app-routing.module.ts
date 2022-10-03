import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ChoixCentreComponent } from './choix-centre/choix-centre.component';
import { ListeCentreComponent } from './liste-centre/liste-centre.component';

const routes: Routes = [
  {path: "public", component: ListeCentreComponent},
  {path: "public/detail/:id", component: ChoixCentreComponent}
  //{path: '', redirectTo: '/public', pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

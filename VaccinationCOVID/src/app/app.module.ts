import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { ListeCentreComponent } from './liste-centre/liste-centre.component';
import { ChoixCentreComponent } from './choix-centre/choix-centre.component';
import { ChoixVilleComponent } from './choix-ville/choix-ville.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { BoardMedecinComponent } from './board-medecin/board-medecin.component';
import { authInterceptorProviders } from './_helpers/auth.interceptor';


@NgModule({
  declarations: [
    AppComponent,
    ListeCentreComponent,
    ChoixCentreComponent,
    ChoixVilleComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardAdminComponent,
    BoardModeratorComponent,
    BoardMedecinComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
  
})
export class AppModule { }

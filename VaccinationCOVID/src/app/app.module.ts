import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ErrorHandler } from '@angular/core';
import { HttpClientModule , HTTP_INTERCEPTORS} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

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
import { AuthInterceptor } from './_helpers/auth.interceptor';
import { ReactiveFormsModule } from '@angular/forms';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { BoardMedecinComponent } from './board-medecin/board-medecin.component';
import { BoardSuperadminComponent } from './board-superadmin/board-superadmin.component';
import { RendezVousComponent } from './rendez-vous/rendez-vous.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { MatNativeDateModule, MAT_DATE_LOCALE } from '@angular/material/core';
import { HashLocationStrategy, LocationStrategy } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { GlobalErrorHandlerService } from './global-error-handler.service';
import { GeneralHttpInterceptorService } from './general-http-interceptor.service';
import { ModalComponent } from './modal/modal.component';

@NgModule({
  declarations: [
    AppComponent,
    ChoixCentreComponent,
    ChoixVilleComponent,
    ListeCentreComponent,
    RendezVousComponent,
    PageNotFoundComponent,
    ChoixCentreComponent,
    ChoixVilleComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardAdminComponent,
    BoardMedecinComponent,
    BoardSuperadminComponent,
    ModalComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatDatepickerModule,
    MatFormFieldModule,
    MatNativeDateModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,
    MatProgressBarModule
  ],
  providers: [ {provide: LocationStrategy, useClass: HashLocationStrategy}, //permet d'éviter whitelabel lors du refresh de la page /public
  {provide: MAT_DATE_LOCALE, useValue: 'fr-FR' },
  { provide: HTTP_INTERCEPTORS, useClass: GeneralHttpInterceptorService, multi: true },
  { provide: ErrorHandler, useClass:GlobalErrorHandlerService},
  authInterceptorProviders],
  bootstrap: [AppComponent]
  
})
export class AppModule { }

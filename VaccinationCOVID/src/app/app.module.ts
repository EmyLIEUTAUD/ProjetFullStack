import { BrowserModule } from '@angular/platform-browser';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ChoixCentreComponent } from './choix-centre/choix-centre.component';
import { ChoixVilleComponent } from './choix-ville/choix-ville.component';
import { RendezVousComponent } from './rendez-vous/rendez-vous.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import {MatDatepickerModule} from '@angular/material/datepicker';
import { ListeCentreComponent } from './liste-centre/liste-centre.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';


@NgModule({
    declarations: [
        AppComponent,
        ChoixCentreComponent,
        ChoixVilleComponent,
        RendezVousComponent,
        PageNotFoundComponent

    ],
    providers: [],
    bootstrap: [AppComponent],
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
        BrowserAnimationsModule,
    ]
    
})
export class AppModule { }

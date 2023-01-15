import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpErrorResponse } from '@angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { ModalComponent } from './modal/modal.component';
import { ModalErrorLoginComponent } from './modal-error-login/modal-error-login.component';

@Injectable({
  providedIn: 'root'
})
export class GeneralHttpInterceptorService implements HttpInterceptor {

  modalRef: MdbModalRef<ModalComponent> | null = null;
  modalRedLogin: MdbModalRef<ModalErrorLoginComponent> | null = null;
  infos = '';
  temps = 0;
  message = '';

  constructor(public router: Router, private modalService: MdbModalService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
    req = req.clone();
    return next.handle(req).pipe(
    catchError((error) => {

        let handled: boolean = false;
        console.error(error);
            switch (error.status) {
              case 401:    //unauthorized
                console.log("erreur 401 : non authorisé")
                if(window.location.href == "http://localhost:4200/#/login"){
                  this.modalRedLogin = this.modalService.open(ModalErrorLoginComponent);
                  console.log("Erreur de login")
                }
                else{
                  this.router.navigateByUrl("/login");
                  console.log(`redirect to login`);
                }
                handled = true;
                break;
              case 403:     //forbidden
                this.router.navigateByUrl("/login");
                console.log(`redirect to login`);
                handled = true;
                break;
              case 304:  //not modified
                console.log("on ne met pas à jour la ressource");
                handled = true;
                break;
              case 412:  //precondition failed
                this.modalRef = this.modalService.open(ModalComponent);
                console.log("proposer à l'utilisateur de recharger la ressource");
                handled = true;
                break;
              case 429: // too many requests
                this.temps =  error.headers.get('X-Rate-Limit-Retry-After-Seconds');
                let url: string;
                url = "/queue/" + this.temps;
                this.router.navigateByUrl(url);
                console.log("rediriger l'utilisateur vers la file d'attente");
                handled = true;
                break;
              }

        if (handled) {
          console.log('return back ');
          return of(error);
        } else {
          console.log('throw error back to to the subscriber');
          return throwError(error);
        }

      })
    )
  }

}

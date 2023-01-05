import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpErrorResponse } from '@Angular/common/http';
import { Observable, of, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { Router } from '@angular/router';
import { JwtInterceptor } from './_helpers/jwt.interceptor';
import { MdbModalRef, MdbModalService } from 'mdb-angular-ui-kit/modal';
import { ModalComponent } from './modal/modal.component';

@Injectable({
  providedIn: 'root'
})
export class GeneralHttpInterceptorService implements HttpInterceptor {

  modalRef: MdbModalRef<ModalComponent> | null = null;

  constructor(public router: Router) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
    console.log("J'intercepte les erreurs http");
    const token: string = 'invald token';
    //req = req.clone({ headers: req.headers.set('Authorization', 'Bearer ' + token) });
    req = req.clone();
    return next.handle(req).pipe(
    catchError((error) => {

        let handled: boolean = false;
        console.error(error);
            console.log("j'ai une erreur");
            console.log(`error status : ${error.status} ${error.statusText}`);
            switch (error.status) {
              case 401:    //unauthorized
                console.log("erreur 401 : non authorisé")
                this.router.navigateByUrl("/login");
                console.log(`redirect to login`);
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
                console.log("proposer à l'utilisateur de recharger la ressource");
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
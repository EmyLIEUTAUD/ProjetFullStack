import { Injectable } from '@angular/core';
import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs';

import { AuthenticationService } from '../_services/authentication.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Injectable()
export class JwtInterceptor implements HttpInterceptor {
    constructor(private authenticationService: AuthenticationService,
        private tokenstorage: TokenStorageService,
        ) { }

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

        /***
         * Intercepter les requêtes pour injecter le token
         */

        // add authorization header with jwt token if available
        const currentUser = this.authenticationService.currentUserValue;
        const token = this.tokenstorage.getAuthToken();
        
       
            let jwtToken = request.clone({
                setHeaders: {
                    Authorization: `Bearer `+ token
                    
                }
            });            

        return next.handle(jwtToken);
    }
}
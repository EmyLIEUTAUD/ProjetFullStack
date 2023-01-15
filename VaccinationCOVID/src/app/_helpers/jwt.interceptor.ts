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
        let tokentest = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdXBlckFkbWluQGdtYWlsLmNvbSIsImV4cCI6MTY3MzMxMDQzNCwiaWF0IjoxNjczMjkyNDM0LCJhdXRob3JpdGllcyI6WyJTVVBFUl9BRE1JTiJdfQ.hX0GyND5sOpM2dry1VGphfDlQrAgU-EWMo1rUq4UuaK_LisWGZDCMRWnI_ichFRpgK4h5H-XoN4k368YQjPusg'

        // add authorization header with jwt token if available
        const currentUser = this.authenticationService.currentUserValue;
        const token = this.tokenstorage.getAuthToken();
        
       
            let jwtToken = request.clone({
                setHeaders: {
                    //Authorization: `Bearer ${currentUser.token}`
                    Authorization: `Bearer `+ token
                    
                }
            });
            console.log("token : "+token)
            

        return next.handle(jwtToken);
    }
}
import { HTTP_INTERCEPTORS, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpHandler, HttpRequest } from '@angular/common/http';

import { Observable } from 'rxjs';

const TOKEN_HEADER_KEY = 'Authorization';       // for Spring Boot back-end
import { TokenStorageService } from '../_services/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptor implements HttpInterceptor {

  constructor(private token: TokenStorageService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> { 
    let tokentest = 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzdXBlckFkbWluQGdtYWlsLmNvbSIsImV4cCI6MTY3MzMxMDQzNCwiaWF0IjoxNjczMjkyNDM0LCJhdXRob3JpdGllcyI6WyJTVVBFUl9BRE1JTiJdfQ.hX0GyND5sOpM2dry1VGphfDlQrAgU-EWMo1rUq4UuaK_LisWGZDCMRWnI_ichFRpgK4h5H-XoN4k368YQjPusg'

    let authReq = req;
    const token = this.token.getAuthToken();
    if (token != null) {
      authReq = req.clone({ headers: req.headers.set(TOKEN_HEADER_KEY, 'Bearer ' + tokentest) });
      console.log(token)
    }
    return next.handle(authReq);
  }
}

export const authInterceptorProviders = [
  { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
];
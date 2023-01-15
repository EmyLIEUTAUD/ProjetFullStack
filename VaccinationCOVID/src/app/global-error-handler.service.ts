import { Injectable, ErrorHandler } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GlobalErrorHandlerService implements ErrorHandler{

  constructor() { }

  handleError(error: Error | HttpErrorResponse) {
    console.log('GlobalErrorHandlerService');
    console.error(error);
  }

}

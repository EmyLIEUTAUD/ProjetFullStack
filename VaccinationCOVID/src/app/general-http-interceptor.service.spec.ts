import { TestBed } from '@angular/core/testing';

import { GeneralHttpInterceptorService } from './general-http-interceptor.service';

describe('GeneralHttpInterceptorService', () => {
  let service: GeneralHttpInterceptorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GeneralHttpInterceptorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

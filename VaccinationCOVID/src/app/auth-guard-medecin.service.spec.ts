import { TestBed } from '@angular/core/testing';

import { AuthGuardMedecinService } from './auth-guard-medecin.service';

describe('AuthGuardMedecinService', () => {
  let service: AuthGuardMedecinService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthGuardMedecinService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

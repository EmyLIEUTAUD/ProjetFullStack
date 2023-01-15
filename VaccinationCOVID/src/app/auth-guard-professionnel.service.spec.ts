import { TestBed } from '@angular/core/testing';

import { AuthGuardProfessionnelService } from './auth-guard-professionnel.service';

describe('AuthGuardProfessionnelService', () => {
  let service: AuthGuardProfessionnelService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthGuardProfessionnelService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

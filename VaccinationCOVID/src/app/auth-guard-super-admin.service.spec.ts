import { TestBed } from '@angular/core/testing';

import { AuthGuardSuperAdminService } from './auth-guard-super-admin.service';

describe('AuthGuardSuperAdminService', () => {
  let service: AuthGuardSuperAdminService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AuthGuardSuperAdminService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { VaccinationAdminServiceService } from './vaccination-admin-service.service';

describe('VaccinationAdminServiceService', () => {
  let service: VaccinationAdminServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(VaccinationAdminServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

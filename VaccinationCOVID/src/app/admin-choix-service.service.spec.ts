import { TestBed } from '@angular/core/testing';

import { AdminChoixServiceService } from './admin-choix-service.service';

describe('AdminChoixServiceService', () => {
  let service: AdminChoixServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminChoixServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

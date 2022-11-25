import { TestBed } from '@angular/core/testing';

import { CentreChoixServiceService } from './centre-choix-service.service';

describe('CentreChoixServiceService', () => {
  let service: CentreChoixServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CentreChoixServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

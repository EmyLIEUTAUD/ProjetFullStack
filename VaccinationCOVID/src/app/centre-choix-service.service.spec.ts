import { TestBed } from '@angular/core/testing';

import { CentreChoisieService } from './centre-choix-service.service';

describe('CentreChoixServiceService', () => {
  let service: CentreChoisieService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CentreChoisieService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

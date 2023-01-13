import { TestBed } from '@angular/core/testing';

import { ProfessionnelsService } from './professionnels.service';

describe('ProfessionnelsService', () => {
  let service: ProfessionnelsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProfessionnelsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { ChoixdelavilleService } from './choixdelaville.service';

describe('ChoixdelavilleService', () => {
  let service: ChoixdelavilleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ChoixdelavilleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

import { TestBed } from '@angular/core/testing';

import { EnvoiFormulaireService } from './envoi-formulaire.service';

describe('EnvoiFormulaireService', () => {
  let service: EnvoiFormulaireService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EnvoiFormulaireService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

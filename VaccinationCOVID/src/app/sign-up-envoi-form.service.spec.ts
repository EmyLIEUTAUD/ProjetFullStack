import { TestBed } from '@angular/core/testing';

import { SignUpEnvoiFormService } from './sign-up-envoi-form.service';

describe('SignUpEnvoiFormService', () => {
  let service: SignUpEnvoiFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SignUpEnvoiFormService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

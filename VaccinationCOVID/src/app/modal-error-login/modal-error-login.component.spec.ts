import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalErrorLoginComponent } from './modal-error-login.component';

describe('ModalErrorLoginComponent', () => {
  let component: ModalErrorLoginComponent;
  let fixture: ComponentFixture<ModalErrorLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalErrorLoginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalErrorLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

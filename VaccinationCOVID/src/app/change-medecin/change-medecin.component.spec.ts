import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeMedecinComponent } from './change-medecin.component';

describe('ChangeMedecinComponent', () => {
  let component: ChangeMedecinComponent;
  let fixture: ComponentFixture<ChangeMedecinComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeMedecinComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChangeMedecinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

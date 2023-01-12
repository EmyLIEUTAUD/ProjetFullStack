import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalMedecinPlanningComponent } from './modal-medecin-planning.component';

describe('ModalMedecinPlanningComponent', () => {
  let component: ModalMedecinPlanningComponent;
  let fixture: ComponentFixture<ModalMedecinPlanningComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalMedecinPlanningComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalMedecinPlanningComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

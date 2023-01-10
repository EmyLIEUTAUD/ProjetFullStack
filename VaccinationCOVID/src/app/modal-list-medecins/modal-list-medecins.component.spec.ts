import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalListMedecinsComponent } from './modal-list-medecins.component';

describe('ModalListMedecinsComponent', () => {
  let component: ModalListMedecinsComponent;
  let fixture: ComponentFixture<ModalListMedecinsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalListMedecinsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalListMedecinsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

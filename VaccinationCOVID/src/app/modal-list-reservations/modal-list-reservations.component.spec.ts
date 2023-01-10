import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalListReservationsComponent } from './modal-list-reservations.component';

describe('ModalListReservationsComponent', () => {
  let component: ModalListReservationsComponent;
  let fixture: ComponentFixture<ModalListReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalListReservationsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalListReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalSuperAdminComponent } from './modal-super-admin.component';

describe('ModalSuperAdminComponent', () => {
  let component: ModalSuperAdminComponent;
  let fixture: ComponentFixture<ModalSuperAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ModalSuperAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ModalSuperAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

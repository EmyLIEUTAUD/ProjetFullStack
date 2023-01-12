import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CentreAdminComponent } from './centre-admin.component';

describe('CentreAdminComponent', () => {
  let component: CentreAdminComponent;
  let fixture: ComponentFixture<CentreAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CentreAdminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CentreAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

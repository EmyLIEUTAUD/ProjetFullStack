import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChangeCentreComponent } from './change-centre.component';

describe('ChangeCentreComponent', () => {
  let component: ChangeCentreComponent;
  let fixture: ComponentFixture<ChangeCentreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChangeCentreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChangeCentreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

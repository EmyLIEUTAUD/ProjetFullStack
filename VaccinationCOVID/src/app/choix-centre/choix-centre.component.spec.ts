import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoixCentreComponent } from './choix-centre.component';

describe('ChoixCentreComponent', () => {
  let component: ChoixCentreComponent;
  let fixture: ComponentFixture<ChoixCentreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChoixCentreComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChoixCentreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

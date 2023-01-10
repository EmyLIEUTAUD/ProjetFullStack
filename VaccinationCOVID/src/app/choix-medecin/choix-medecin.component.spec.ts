import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoixMedecinComponent } from './choix-medecin.component';

describe('ChoixMedecinComponent', () => {
  let component: ChoixMedecinComponent;
  let fixture: ComponentFixture<ChoixMedecinComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChoixMedecinComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChoixMedecinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

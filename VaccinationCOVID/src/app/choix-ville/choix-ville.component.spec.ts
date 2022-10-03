import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoixVilleComponent } from './choix-ville.component';

describe('ChoixVilleComponent', () => {
  let component: ChoixVilleComponent;
  let fixture: ComponentFixture<ChoixVilleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChoixVilleComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ChoixVilleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

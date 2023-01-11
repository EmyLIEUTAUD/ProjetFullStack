import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardMedecinComponent } from './board-medecin.component';

describe('BoardMedecinComponent', () => {
  let component: BoardMedecinComponent;
  let fixture: ComponentFixture<BoardMedecinComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoardMedecinComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BoardMedecinComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

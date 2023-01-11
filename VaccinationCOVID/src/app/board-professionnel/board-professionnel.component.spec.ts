import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardProfessionnelComponent } from './board-professionnel.component';

describe('BoardProfessionnelComponent', () => {
  let component: BoardProfessionnelComponent;
  let fixture: ComponentFixture<BoardProfessionnelComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoardProfessionnelComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BoardProfessionnelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BoardSuperadminComponent } from './board-superadmin.component';

describe('BoardSuperadminComponent', () => {
  let component: BoardSuperadminComponent;
  let fixture: ComponentFixture<BoardSuperadminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BoardSuperadminComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BoardSuperadminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPgComponent } from './admin-pg.component';

describe('AdminPgComponent', () => {
  let component: AdminPgComponent;
  let fixture: ComponentFixture<AdminPgComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminPgComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminPgComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

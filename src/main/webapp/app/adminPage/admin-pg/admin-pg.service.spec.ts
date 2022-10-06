import { TestBed } from '@angular/core/testing';

import { AdminPgService } from './admin-pg.service';

describe('AdminPgService', () => {
  let service: AdminPgService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminPgService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});

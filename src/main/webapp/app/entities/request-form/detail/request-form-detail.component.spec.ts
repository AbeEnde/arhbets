import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { RequestFormDetailComponent } from './request-form-detail.component';

describe('RequestForm Management Detail Component', () => {
  let comp: RequestFormDetailComponent;
  let fixture: ComponentFixture<RequestFormDetailComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RequestFormDetailComponent],
      providers: [
        {
          provide: ActivatedRoute,
          useValue: { data: of({ requestForm: { id: 123 } }) },
        },
      ],
    })
      .overrideTemplate(RequestFormDetailComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(RequestFormDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load requestForm on init', () => {
      // WHEN
      comp.ngOnInit();

      // THEN
      expect(comp.requestForm).toEqual(expect.objectContaining({ id: 123 }));
    });
  });
});

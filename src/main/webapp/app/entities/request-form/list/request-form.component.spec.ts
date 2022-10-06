import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { RequestFormService } from '../service/request-form.service';

import { RequestFormComponent } from './request-form.component';

describe('RequestForm Management Component', () => {
  let comp: RequestFormComponent;
  let fixture: ComponentFixture<RequestFormComponent>;
  let service: RequestFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [RequestFormComponent],
    })
      .overrideTemplate(RequestFormComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(RequestFormComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(RequestFormService);

    const headers = new HttpHeaders();
    jest.spyOn(service, 'query').mockReturnValue(
      of(
        new HttpResponse({
          body: [{ id: 123 }],
          headers,
        })
      )
    );
  });

  it('Should call load all on init', () => {
    // WHEN
    comp.ngOnInit();

    // THEN
    expect(service.query).toHaveBeenCalled();
    expect(comp.requestForms?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});

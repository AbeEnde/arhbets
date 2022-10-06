import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { of } from 'rxjs';

import { HospitalService } from '../service/hospital.service';

import { HospitalComponent } from './hospital.component';

describe('Hospital Management Component', () => {
  let comp: HospitalComponent;
  let fixture: ComponentFixture<HospitalComponent>;
  let service: HospitalService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [HospitalComponent],
    })
      .overrideTemplate(HospitalComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(HospitalComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(HospitalService);

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
    expect(comp.hospitals?.[0]).toEqual(expect.objectContaining({ id: 123 }));
  });
});

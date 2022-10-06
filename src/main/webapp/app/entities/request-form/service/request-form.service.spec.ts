import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import dayjs from 'dayjs/esm';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IRequestForm, RequestForm } from '../request-form.model';

import { RequestFormService } from './request-form.service';

describe('RequestForm Service', () => {
  let service: RequestFormService;
  let httpMock: HttpTestingController;
  let elemDefault: IRequestForm;
  let expectedResult: IRequestForm | IRequestForm[] | boolean | null;
  let currentDate: dayjs.Dayjs;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RequestFormService);
    httpMock = TestBed.inject(HttpTestingController);
    currentDate = dayjs();

    elemDefault = {
      id: 0,
      name: 'AAAAAAA',
      sex: 'AAAAAAA',
      profession: 'AAAAAAA',
      c_wzone: 'AAAAAAA',
      c_wworeda: 'AAAAAAA',
      c_whealthfacility: 'AAAAAAA',
      firstchoice: 'AAAAAAA',
      secondchoice: 'AAAAAAA',
      thirdchoice: 'AAAAAAA',
      selfexpirence: currentDate,
      saposeexperience: 'AAAAAAA',
      no: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign(
        {
          selfexpirence: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a RequestForm', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
          selfexpirence: currentDate.format(DATE_FORMAT),
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          selfexpirence: currentDate,
        },
        returnedFromService
      );

      service.create(new RequestForm()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a RequestForm', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          name: 'BBBBBB',
          sex: 'BBBBBB',
          profession: 'BBBBBB',
          c_wzone: 'BBBBBB',
          c_wworeda: 'BBBBBB',
          c_whealthfacility: 'BBBBBB',
          firstchoice: 'BBBBBB',
          secondchoice: 'BBBBBB',
          thirdchoice: 'BBBBBB',
          selfexpirence: currentDate.format(DATE_FORMAT),
          saposeexperience: 'BBBBBB',
          no: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          selfexpirence: currentDate,
        },
        returnedFromService
      );

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a RequestForm', () => {
      const patchObject = Object.assign(
        {
          c_wzone: 'BBBBBB',
          c_wworeda: 'BBBBBB',
          firstchoice: 'BBBBBB',
          secondchoice: 'BBBBBB',
          selfexpirence: currentDate.format(DATE_FORMAT),
          saposeexperience: 'BBBBBB',
        },
        new RequestForm()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign(
        {
          selfexpirence: currentDate,
        },
        returnedFromService
      );

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of RequestForm', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          name: 'BBBBBB',
          sex: 'BBBBBB',
          profession: 'BBBBBB',
          c_wzone: 'BBBBBB',
          c_wworeda: 'BBBBBB',
          c_whealthfacility: 'BBBBBB',
          firstchoice: 'BBBBBB',
          secondchoice: 'BBBBBB',
          thirdchoice: 'BBBBBB',
          selfexpirence: currentDate.format(DATE_FORMAT),
          saposeexperience: 'BBBBBB',
          no: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign(
        {
          selfexpirence: currentDate,
        },
        returnedFromService
      );

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a RequestForm', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addRequestFormToCollectionIfMissing', () => {
      it('should add a RequestForm to an empty array', () => {
        const requestForm: IRequestForm = { id: 123 };
        expectedResult = service.addRequestFormToCollectionIfMissing([], requestForm);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(requestForm);
      });

      it('should not add a RequestForm to an array that contains it', () => {
        const requestForm: IRequestForm = { id: 123 };
        const requestFormCollection: IRequestForm[] = [
          {
            ...requestForm,
          },
          { id: 456 },
        ];
        expectedResult = service.addRequestFormToCollectionIfMissing(requestFormCollection, requestForm);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a RequestForm to an array that doesn't contain it", () => {
        const requestForm: IRequestForm = { id: 123 };
        const requestFormCollection: IRequestForm[] = [{ id: 456 }];
        expectedResult = service.addRequestFormToCollectionIfMissing(requestFormCollection, requestForm);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(requestForm);
      });

      it('should add only unique RequestForm to an array', () => {
        const requestFormArray: IRequestForm[] = [{ id: 123 }, { id: 456 }, { id: 8092 }];
        const requestFormCollection: IRequestForm[] = [{ id: 123 }];
        expectedResult = service.addRequestFormToCollectionIfMissing(requestFormCollection, ...requestFormArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const requestForm: IRequestForm = { id: 123 };
        const requestForm2: IRequestForm = { id: 456 };
        expectedResult = service.addRequestFormToCollectionIfMissing([], requestForm, requestForm2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(requestForm);
        expect(expectedResult).toContain(requestForm2);
      });

      it('should accept null and undefined values', () => {
        const requestForm: IRequestForm = { id: 123 };
        expectedResult = service.addRequestFormToCollectionIfMissing([], null, requestForm, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(requestForm);
      });

      it('should return initial array if no RequestForm is added', () => {
        const requestFormCollection: IRequestForm[] = [{ id: 123 }];
        expectedResult = service.addRequestFormToCollectionIfMissing(requestFormCollection, undefined, null);
        expect(expectedResult).toEqual(requestFormCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});

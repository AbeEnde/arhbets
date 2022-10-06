import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IHospital, Hospital } from '../hospital.model';

import { HospitalService } from './hospital.service';

describe('Hospital Service', () => {
  let service: HospitalService;
  let httpMock: HttpTestingController;
  let elemDefault: IHospital;
  let expectedResult: IHospital | IHospital[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(HospitalService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      no: 0,
      hospitalName: 'AAAAAAA',
      code: 'AAAAAAA',
    };
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = Object.assign({}, elemDefault);

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(elemDefault);
    });

    it('should create a Hospital', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Hospital()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Hospital', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          no: 1,
          hospitalName: 'BBBBBB',
          code: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Hospital', () => {
      const patchObject = Object.assign(
        {
          hospitalName: 'BBBBBB',
        },
        new Hospital()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Hospital', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          no: 1,
          hospitalName: 'BBBBBB',
          code: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toContainEqual(expected);
    });

    it('should delete a Hospital', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addHospitalToCollectionIfMissing', () => {
      it('should add a Hospital to an empty array', () => {
        const hospital: IHospital = { id: 123 };
        expectedResult = service.addHospitalToCollectionIfMissing([], hospital);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(hospital);
      });

      it('should not add a Hospital to an array that contains it', () => {
        const hospital: IHospital = { id: 123 };
        const hospitalCollection: IHospital[] = [
          {
            ...hospital,
          },
          { id: 456 },
        ];
        expectedResult = service.addHospitalToCollectionIfMissing(hospitalCollection, hospital);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Hospital to an array that doesn't contain it", () => {
        const hospital: IHospital = { id: 123 };
        const hospitalCollection: IHospital[] = [{ id: 456 }];
        expectedResult = service.addHospitalToCollectionIfMissing(hospitalCollection, hospital);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(hospital);
      });

      it('should add only unique Hospital to an array', () => {
        const hospitalArray: IHospital[] = [{ id: 123 }, { id: 456 }, { id: 59643 }];
        const hospitalCollection: IHospital[] = [{ id: 123 }];
        expectedResult = service.addHospitalToCollectionIfMissing(hospitalCollection, ...hospitalArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const hospital: IHospital = { id: 123 };
        const hospital2: IHospital = { id: 456 };
        expectedResult = service.addHospitalToCollectionIfMissing([], hospital, hospital2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(hospital);
        expect(expectedResult).toContain(hospital2);
      });

      it('should accept null and undefined values', () => {
        const hospital: IHospital = { id: 123 };
        expectedResult = service.addHospitalToCollectionIfMissing([], null, hospital, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(hospital);
      });

      it('should return initial array if no Hospital is added', () => {
        const hospitalCollection: IHospital[] = [{ id: 123 }];
        expectedResult = service.addHospitalToCollectionIfMissing(hospitalCollection, undefined, null);
        expect(expectedResult).toEqual(hospitalCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});

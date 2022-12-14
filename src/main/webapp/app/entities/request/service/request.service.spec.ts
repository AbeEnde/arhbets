import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { IRequest, Request } from '../request.model';

import { RequestService } from './request.service';

describe('Request Service', () => {
  let service: RequestService;
  let httpMock: HttpTestingController;
  let elemDefault: IRequest;
  let expectedResult: IRequest | IRequest[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
    });
    expectedResult = null;
    service = TestBed.inject(RequestService);
    httpMock = TestBed.inject(HttpTestingController);

    elemDefault = {
      id: 0,
      name: 'AAAAAAA',
      sex: 'AAAAAAA',
      profession: 'AAAAAAA',
      cwzone: 'AAAAAAA',
      cwworeda: 'AAAAAAA',
      cwfacility: 'AAAAAAA',
      firstchoice: 'AAAAAAA',
      code1: 'AAAAAAA',
      secondchoice: 'AAAAAAA',
      code2: 'AAAAAAA',
      thirdchoice: 'AAAAAAA',
      code3: 'AAAAAAA',
      expryear: 'AAAAAAA',
      exprmonth: 'AAAAAAA',
      exprday: 'AAAAAAA',
      spexpryear: 'AAAAAAA',
      spexprmonth: 'AAAAAAA',
      spexprday: 'AAAAAAA',
      fileContentType: 'image/png',
      file: 'AAAAAAA',
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

    it('should create a Request', () => {
      const returnedFromService = Object.assign(
        {
          id: 0,
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.create(new Request()).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a Request', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          name: 'BBBBBB',
          sex: 'BBBBBB',
          profession: 'BBBBBB',
          cwzone: 'BBBBBB',
          cwworeda: 'BBBBBB',
          cwfacility: 'BBBBBB',
          firstchoice: 'BBBBBB',
          code1: 'BBBBBB',
          secondchoice: 'BBBBBB',
          code2: 'BBBBBB',
          thirdchoice: 'BBBBBB',
          code3: 'BBBBBB',
          expryear: 'BBBBBB',
          exprmonth: 'BBBBBB',
          exprday: 'BBBBBB',
          spexpryear: 'BBBBBB',
          spexprmonth: 'BBBBBB',
          spexprday: 'BBBBBB',
          file: 'BBBBBB',
        },
        elemDefault
      );

      const expected = Object.assign({}, returnedFromService);

      service.update(expected).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a Request', () => {
      const patchObject = Object.assign(
        {
          profession: 'BBBBBB',
          cwzone: 'BBBBBB',
          cwworeda: 'BBBBBB',
          thirdchoice: 'BBBBBB',
          code3: 'BBBBBB',
          exprmonth: 'BBBBBB',
          spexpryear: 'BBBBBB',
          spexprmonth: 'BBBBBB',
        },
        new Request()
      );

      const returnedFromService = Object.assign(patchObject, elemDefault);

      const expected = Object.assign({}, returnedFromService);

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of Request', () => {
      const returnedFromService = Object.assign(
        {
          id: 1,
          name: 'BBBBBB',
          sex: 'BBBBBB',
          profession: 'BBBBBB',
          cwzone: 'BBBBBB',
          cwworeda: 'BBBBBB',
          cwfacility: 'BBBBBB',
          firstchoice: 'BBBBBB',
          code1: 'BBBBBB',
          secondchoice: 'BBBBBB',
          code2: 'BBBBBB',
          thirdchoice: 'BBBBBB',
          code3: 'BBBBBB',
          expryear: 'BBBBBB',
          exprmonth: 'BBBBBB',
          exprday: 'BBBBBB',
          spexpryear: 'BBBBBB',
          spexprmonth: 'BBBBBB',
          spexprday: 'BBBBBB',
          file: 'BBBBBB',
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

    it('should delete a Request', () => {
      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult);
    });

    describe('addRequestToCollectionIfMissing', () => {
      it('should add a Request to an empty array', () => {
        const request: IRequest = { id: 123 };
        expectedResult = service.addRequestToCollectionIfMissing([], request);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(request);
      });

      it('should not add a Request to an array that contains it', () => {
        const request: IRequest = { id: 123 };
        const requestCollection: IRequest[] = [
          {
            ...request,
          },
          { id: 456 },
        ];
        expectedResult = service.addRequestToCollectionIfMissing(requestCollection, request);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a Request to an array that doesn't contain it", () => {
        const request: IRequest = { id: 123 };
        const requestCollection: IRequest[] = [{ id: 456 }];
        expectedResult = service.addRequestToCollectionIfMissing(requestCollection, request);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(request);
      });

      it('should add only unique Request to an array', () => {
        const requestArray: IRequest[] = [{ id: 123 }, { id: 456 }, { id: 58019 }];
        const requestCollection: IRequest[] = [{ id: 123 }];
        expectedResult = service.addRequestToCollectionIfMissing(requestCollection, ...requestArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const request: IRequest = { id: 123 };
        const request2: IRequest = { id: 456 };
        expectedResult = service.addRequestToCollectionIfMissing([], request, request2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(request);
        expect(expectedResult).toContain(request2);
      });

      it('should accept null and undefined values', () => {
        const request: IRequest = { id: 123 };
        expectedResult = service.addRequestToCollectionIfMissing([], null, request, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(request);
      });

      it('should return initial array if no Request is added', () => {
        const requestCollection: IRequest[] = [{ id: 123 }];
        expectedResult = service.addRequestToCollectionIfMissing(requestCollection, undefined, null);
        expect(expectedResult).toEqual(requestCollection);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});

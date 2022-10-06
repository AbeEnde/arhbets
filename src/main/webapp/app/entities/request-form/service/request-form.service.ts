import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IRequestForm, getRequestFormIdentifier } from '../request-form.model';

export type EntityResponseType = HttpResponse<IRequestForm>;
export type EntityArrayResponseType = HttpResponse<IRequestForm[]>;

@Injectable({ providedIn: 'root' })
export class RequestFormService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/request-forms');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(requestForm: IRequestForm): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(requestForm);
    return this.http
      .post<IRequestForm>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(requestForm: IRequestForm): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(requestForm);
    return this.http
      .put<IRequestForm>(`${this.resourceUrl}/${getRequestFormIdentifier(requestForm) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  partialUpdate(requestForm: IRequestForm): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(requestForm);
    return this.http
      .patch<IRequestForm>(`${this.resourceUrl}/${getRequestFormIdentifier(requestForm) as number}`, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IRequestForm>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IRequestForm[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addRequestFormToCollectionIfMissing(
    requestFormCollection: IRequestForm[],
    ...requestFormsToCheck: (IRequestForm | null | undefined)[]
  ): IRequestForm[] {
    const requestForms: IRequestForm[] = requestFormsToCheck.filter(isPresent);
    if (requestForms.length > 0) {
      const requestFormCollectionIdentifiers = requestFormCollection.map(requestFormItem => getRequestFormIdentifier(requestFormItem)!);
      const requestFormsToAdd = requestForms.filter(requestFormItem => {
        const requestFormIdentifier = getRequestFormIdentifier(requestFormItem);
        if (requestFormIdentifier == null || requestFormCollectionIdentifiers.includes(requestFormIdentifier)) {
          return false;
        }
        requestFormCollectionIdentifiers.push(requestFormIdentifier);
        return true;
      });
      return [...requestFormsToAdd, ...requestFormCollection];
    }
    return requestFormCollection;
  }

  protected convertDateFromClient(requestForm: IRequestForm): IRequestForm {
    return Object.assign({}, requestForm, {
      selfexpirence: requestForm.selfexpirence?.isValid() ? requestForm.selfexpirence.format(DATE_FORMAT) : undefined,
    });
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.selfexpirence = res.body.selfexpirence ? dayjs(res.body.selfexpirence) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((requestForm: IRequestForm) => {
        requestForm.selfexpirence = requestForm.selfexpirence ? dayjs(requestForm.selfexpirence) : undefined;
      });
    }
    return res;
  }
}

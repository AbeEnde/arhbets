import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { isPresent } from 'app/core/util/operators';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IHospital, getHospitalIdentifier } from '../hospital.model';

export type EntityResponseType = HttpResponse<IHospital>;
export type EntityArrayResponseType = HttpResponse<IHospital[]>;

@Injectable({ providedIn: 'root' })
export class HospitalService {
  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/hospitals');

  constructor(protected http: HttpClient, protected applicationConfigService: ApplicationConfigService) {}

  create(hospital: IHospital): Observable<EntityResponseType> {
    return this.http.post<IHospital>(this.resourceUrl, hospital, { observe: 'response' });
  }

  update(hospital: IHospital): Observable<EntityResponseType> {
    return this.http.put<IHospital>(`${this.resourceUrl}/${getHospitalIdentifier(hospital) as number}`, hospital, { observe: 'response' });
  }

  partialUpdate(hospital: IHospital): Observable<EntityResponseType> {
    return this.http.patch<IHospital>(`${this.resourceUrl}/${getHospitalIdentifier(hospital) as number}`, hospital, {
      observe: 'response',
    });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IHospital>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IHospital[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  addHospitalToCollectionIfMissing(hospitalCollection: IHospital[], ...hospitalsToCheck: (IHospital | null | undefined)[]): IHospital[] {
    const hospitals: IHospital[] = hospitalsToCheck.filter(isPresent);
    if (hospitals.length > 0) {
      const hospitalCollectionIdentifiers = hospitalCollection.map(hospitalItem => getHospitalIdentifier(hospitalItem)!);
      const hospitalsToAdd = hospitals.filter(hospitalItem => {
        const hospitalIdentifier = getHospitalIdentifier(hospitalItem);
        if (hospitalIdentifier == null || hospitalCollectionIdentifiers.includes(hospitalIdentifier)) {
          return false;
        }
        hospitalCollectionIdentifiers.push(hospitalIdentifier);
        return true;
      });
      return [...hospitalsToAdd, ...hospitalCollection];
    }
    return hospitalCollection;
  }
}

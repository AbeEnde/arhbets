import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { IRequest } from 'app/RequestForm/request.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HospitalService {

  protected request ="localhist:8080//api/requests"

protected resourceUrl = this.applicationConfigService.getEndpointFor('api/hospitals');
  constructor(protected http: HttpClient,protected applicationConfigService: ApplicationConfigService) {}

 /*sendReq(req: any): Observable<any> {
    return this.http.post<any>(this.resourceUrl, req, { observe: 'response' });
  } */

  //@typescript-eslint/no-unsafe-return
  sendHosp(req: IRequest): Observable<any> {
    return this.http.post<IRequest>(this.resourceUrl, req, { observe: 'response' });
  }
}

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { IRequest } from '../request.model';


@Injectable({
  providedIn: 'root'
})
export class RequestService {
  data:any;
protected request ="localhist:8080//api/requests"
protected resourceUrl = this.applicationConfigService.getEndpointFor('api/addemp');
  constructor(protected http: HttpClient,protected applicationConfigService: ApplicationConfigService) {}

 /*sendReq(req: any): Observable<any> {
    return this.http.post<any>(this.resourceUrl, req, { observe: 'response' });
  } */

  //@typescript-eslint/no-unsafe-return
  sendReq(req: IRequest): Observable<any> {
    return this.http.post<IRequest>(this.resourceUrl, req, { observe: 'response' });
  }

  hold(dat:any):any{
    this.data = dat;
  }
  getData():any{
    return this.data;
  }

}

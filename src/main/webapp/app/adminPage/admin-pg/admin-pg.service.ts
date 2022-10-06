import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminPgService {

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/getAllData');
  protected resultRec = this.applicationConfigService.getEndpointFor('api/results');
  protected resultgetAll = this.applicationConfigService.getEndpointFor('api/getRes');



  constructor(protected http: HttpClient,protected applicationConfigService: ApplicationConfigService) {}

  getAllData(): Observable<any> {
    return this.http.get<any>(this.resourceUrl, { observe: 'response' });
  } 


  saveRes(results:any):Observable<any>{
    return this.http.post<any>(this.resultRec,results,{ observe: 'response' })
  }

  getresult():Observable<any>{
return this.http.get<any>(this.resultgetAll, {observe:'response'});
  }
 
}

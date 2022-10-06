import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';

@Injectable({
    providedIn: 'root'
})
export class choiceService{
    protected resourceUrl = this.applicationConfigService.getEndpointFor('api/addChoice');
    protected hospApi = this.applicationConfigService.getEndpointFor('api/hospitals')
    constructor(protected http: HttpClient,protected applicationConfigService: ApplicationConfigService) {}

    savechoice(data:any):any{
        return this.http.post<any>(this.resourceUrl,data,{ observe: 'response' })
 
    }
    getHosp():any{
    return this.http.get<any>(this.hospApi,{observe: 'response'})
      
    }

}
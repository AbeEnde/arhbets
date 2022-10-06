import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApplicationConfigService } from 'app/core/config/application-config.service';

@Injectable({
    providedIn: 'root'
})

export class SelectedService{
    protected resourceUrl = this.applicationConfigService.getEndpointFor('api/getSelectedEmp');

    constructor(protected http: HttpClient,protected applicationConfigService: ApplicationConfigService) {}
getSelected():any{
    return this.http.get<any>(this.resourceUrl ,{observe: 'response'})
}
}
import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IRequestForm, RequestForm } from '../request-form.model';
import { RequestFormService } from '../service/request-form.service';

@Injectable({ providedIn: 'root' })
export class RequestFormRoutingResolveService implements Resolve<IRequestForm> {
  constructor(protected service: RequestFormService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IRequestForm> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((requestForm: HttpResponse<RequestForm>) => {
          if (requestForm.body) {
            return of(requestForm.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new RequestForm());
  }
}

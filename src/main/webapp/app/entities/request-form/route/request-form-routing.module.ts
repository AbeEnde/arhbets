import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { RequestFormComponent } from '../list/request-form.component';
import { RequestFormDetailComponent } from '../detail/request-form-detail.component';
import { RequestFormUpdateComponent } from '../update/request-form-update.component';
import { RequestFormRoutingResolveService } from './request-form-routing-resolve.service';

const requestFormRoute: Routes = [
  {
    path: '',
    component: RequestFormComponent,
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: RequestFormDetailComponent,
    resolve: {
      requestForm: RequestFormRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: RequestFormUpdateComponent,
    resolve: {
      requestForm: RequestFormRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: RequestFormUpdateComponent,
    resolve: {
      requestForm: RequestFormRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(requestFormRoute)],
  exports: [RouterModule],
})
export class RequestFormRoutingModule {}

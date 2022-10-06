import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'request-form',
        data: { pageTitle: 'ahbetsApp.requestForm.home.title' },
        loadChildren: () => import('./request-form/request-form.module').then(m => m.RequestFormModule),
      },
      {
        path: 'hospital',
        data: { pageTitle: 'ahbetsApp.hospital.home.title' },
        loadChildren: () => import('./hospital/hospital.module').then(m => m.HospitalModule),
      },
      {
        path: 'department',
        data: { pageTitle: 'ahbetsApp.department.home.title' },
        loadChildren: () => import('./department/department.module').then(m => m.DepartmentModule),
      },
      {
        path: 'request',
        data: { pageTitle: 'ahbetsApp.request.home.title' },
        loadChildren: () => import('./request/request.module').then(m => m.RequestModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}

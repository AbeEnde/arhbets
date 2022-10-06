import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { errorRoute } from './layouts/error/error.route';
import { navbarRoute } from './layouts/navbar/navbar.route';
import { DEBUG_INFO_ENABLED } from 'app/app.constants';
import { Authority } from 'app/config/authority.constants';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';

@NgModule({
  imports: [
    RouterModule.forRoot(
      [
        

        {
          path:'request',
          loadChildren: () => import('./RequestForm/request/request.module').then(m => m.RequestModule),


        },

        {
          path:'choice',
        loadChildren: () => import('./choice/choice.module').then(m => m.ChoiceModule),
        },

      

        {
          path:'adminPg',
          loadChildren: () => import('./adminPage/admin-pg/admin-pg.module').then(m => m.AdminPgModule),


        },

        {
          path:'hospital',
          loadChildren: () => import('./hospital/hospital/hospital.module').then(m => m.HospitalModule),


        },
       
        {
          path:'selected',
          loadChildren: () => import('./selected/selected.module').then(m => m.SelectedModule),


        },

        {
          path: 'admin',
          data: {
            authorities: [Authority.ADMIN],
          },
          canActivate: [UserRouteAccessService],
          loadChildren: () => import('./admin/admin-routing.module').then(m => m.AdminRoutingModule),
        },
        {
          path: 'account',
          loadChildren: () => import('./account/account.module').then(m => m.AccountModule),
        },
        {
          path: 'login',
          loadChildren: () => import('./login/login.module').then(m => m.LoginModule),
        },
        {
          path: '',
          loadChildren: () => import(`./entities/entity-routing.module`).then(m => m.EntityRoutingModule),
        },
        navbarRoute,
        ...errorRoute,
      ],
      { enableTracing: DEBUG_INFO_ENABLED }
    ),
  ],
  exports: [RouterModule],
})
export class AppRoutingModule {}

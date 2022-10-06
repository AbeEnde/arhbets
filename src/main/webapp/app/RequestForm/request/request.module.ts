import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { SharedModule } from 'app/shared/shared.module';
import { reqroute } from './request.route';



@NgModule({
  declarations: [],
  imports: [
     CommonModule, SharedModule, RouterModule.forChild(reqroute)

  ]
})
export class RequestModule { }

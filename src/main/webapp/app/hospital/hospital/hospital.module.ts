import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { hosproute } from './hospital.route';
import { SharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';


@NgModule({
  declarations: [],
  imports: [
    CommonModule, SharedModule, RouterModule.forChild(hosproute)

  ]
})
export class HospitalModule { }

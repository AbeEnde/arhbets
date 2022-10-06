import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { selectedRoute } from './selected.route';


@NgModule({
  declarations: [],
  imports: [
    CommonModule,SharedModule,RouterModule.forChild(selectedRoute)
  ]
})
export class SelectedModule { }

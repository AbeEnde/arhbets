import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SharedModule } from 'app/shared/shared.module';
import { RouterModule } from '@angular/router';
import { choiceRoute } from './choice.route';

@NgModule({
  declarations: [],
  imports: [
    CommonModule, SharedModule, RouterModule.forChild(choiceRoute)
  ]
})
export class ChoiceModule { }

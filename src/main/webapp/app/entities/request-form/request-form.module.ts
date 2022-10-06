import { NgModule } from '@angular/core';
import { SharedModule } from 'app/shared/shared.module';
import { RequestFormComponent } from './list/request-form.component';
import { RequestFormDetailComponent } from './detail/request-form-detail.component';
import { RequestFormUpdateComponent } from './update/request-form-update.component';
import { RequestFormDeleteDialogComponent } from './delete/request-form-delete-dialog.component';
import { RequestFormRoutingModule } from './route/request-form-routing.module';

@NgModule({
  imports: [SharedModule, RequestFormRoutingModule],
  declarations: [RequestFormComponent, RequestFormDetailComponent, RequestFormUpdateComponent, RequestFormDeleteDialogComponent],
  entryComponents: [RequestFormDeleteDialogComponent],
})
export class RequestFormModule {}

import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IRequestForm } from '../request-form.model';
import { RequestFormService } from '../service/request-form.service';

@Component({
  templateUrl: './request-form-delete-dialog.component.html',
})
export class RequestFormDeleteDialogComponent {
  requestForm?: IRequestForm;

  constructor(protected requestFormService: RequestFormService, protected activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.requestFormService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}

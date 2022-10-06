import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IRequestForm } from '../request-form.model';
import { RequestFormService } from '../service/request-form.service';
import { RequestFormDeleteDialogComponent } from '../delete/request-form-delete-dialog.component';

@Component({
  selector: 'jhi-request-form',
  templateUrl: './request-form.component.html',
})
export class RequestFormComponent implements OnInit {
  requestForms?: IRequestForm[];
  isLoading = false;

  constructor(protected requestFormService: RequestFormService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.requestFormService.query().subscribe({
      next: (res: HttpResponse<IRequestForm[]>) => {
        this.isLoading = false;
        this.requestForms = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IRequestForm): number {
    return item.id!;
  }

  delete(requestForm: IRequestForm): void {
    const modalRef = this.modalService.open(RequestFormDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.requestForm = requestForm;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}

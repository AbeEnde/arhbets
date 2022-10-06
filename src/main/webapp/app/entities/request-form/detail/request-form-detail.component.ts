import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IRequestForm } from '../request-form.model';

@Component({
  selector: 'jhi-request-form-detail',
  templateUrl: './request-form-detail.component.html',
})
export class RequestFormDetailComponent implements OnInit {
  requestForm: IRequestForm | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requestForm }) => {
      this.requestForm = requestForm;
    });
  }

  previousState(): void {
    window.history.back();
  }
}

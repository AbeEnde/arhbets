import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IRequestForm, RequestForm } from '../request-form.model';
import { RequestFormService } from '../service/request-form.service';

@Component({
  selector: 'jhi-request-form-update',
  templateUrl: './request-form-update.component.html',
})
export class RequestFormUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required]],
    sex: [],
    profession: [],
    c_wzone: [],
    c_wworeda: [],
    c_whealthfacility: [],
    firstchoice: [],
    secondchoice: [],
    thirdchoice: [],
    selfexpirence: [],
    saposeexperience: [],
    no: [],
  });

  constructor(protected requestFormService: RequestFormService, protected activatedRoute: ActivatedRoute, protected fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ requestForm }) => {
      this.updateForm(requestForm);
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const requestForm = this.createFromForm();
    if (requestForm.id !== undefined) {
      this.subscribeToSaveResponse(this.requestFormService.update(requestForm));
    } else {
      this.subscribeToSaveResponse(this.requestFormService.create(requestForm));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequestForm>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(requestForm: IRequestForm): void {
    this.editForm.patchValue({
      id: requestForm.id,
      name: requestForm.name,
      sex: requestForm.sex,
      profession: requestForm.profession,
      c_wzone: requestForm.c_wzone,
      c_wworeda: requestForm.c_wworeda,
      c_whealthfacility: requestForm.c_whealthfacility,
      firstchoice: requestForm.firstchoice,
      secondchoice: requestForm.secondchoice,
      thirdchoice: requestForm.thirdchoice,
      selfexpirence: requestForm.selfexpirence,
      saposeexperience: requestForm.saposeexperience,
      no: requestForm.no,
    });
  }

  protected createFromForm(): IRequestForm {
    return {
      ...new RequestForm(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      sex: this.editForm.get(['sex'])!.value,
      profession: this.editForm.get(['profession'])!.value,
      c_wzone: this.editForm.get(['c_wzone'])!.value,
      c_wworeda: this.editForm.get(['c_wworeda'])!.value,
      c_whealthfacility: this.editForm.get(['c_whealthfacility'])!.value,
      firstchoice: this.editForm.get(['firstchoice'])!.value,
      secondchoice: this.editForm.get(['secondchoice'])!.value,
      thirdchoice: this.editForm.get(['thirdchoice'])!.value,
      selfexpirence: this.editForm.get(['selfexpirence'])!.value,
      saposeexperience: this.editForm.get(['saposeexperience'])!.value,
      no: this.editForm.get(['no'])!.value,
    };
  }
}

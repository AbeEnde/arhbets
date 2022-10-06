import { Component, OnInit, ElementRef } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import { IRequest, Request } from '../request.model';
import { RequestService } from '../service/request.service';
import { AlertError } from 'app/shared/alert/alert-error.model';
import { EventManager, EventWithContent } from 'app/core/util/event-manager.service';
import { DataUtils, FileLoadError } from 'app/core/util/data-util.service';

@Component({
  selector: 'jhi-request-update',
  templateUrl: './request-update.component.html',
})
export class RequestUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    sex: [],
    profession: [],
    cwzone: [],
    cwworeda: [],
    cwfacility: [],
    firstchoice: [],
    code1: [],
    secondchoice: [],
    code2: [],
    thirdchoice: [],
    code3: [],
    expryear: [],
    exprmonth: [],
    exprday: [],
    spexpryear: [],
    spexprmonth: [],
    spexprday: [],
    file: [],
    fileContentType: [],
  });

  constructor(
    protected dataUtils: DataUtils,
    protected eventManager: EventManager,
    protected requestService: RequestService,
    protected elementRef: ElementRef,
    protected activatedRoute: ActivatedRoute,
    protected fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ request }) => {
      this.updateForm(request);
    });
  }

  byteSize(base64String: string): string {
    return this.dataUtils.byteSize(base64String);
  }

  openFile(base64String: string, contentType: string | null | undefined): void {
    this.dataUtils.openFile(base64String, contentType);
  }

  setFileData(event: Event, field: string, isImage: boolean): void {
    this.dataUtils.loadFileToForm(event, this.editForm, field, isImage).subscribe({
      error: (err: FileLoadError) =>
        this.eventManager.broadcast(new EventWithContent<AlertError>('ahbetsApp.error', { ...err, key: 'error.file.' + err.key })),
    });
  }

  clearInputImage(field: string, fieldContentType: string, idInput: string): void {
    this.editForm.patchValue({
      [field]: null,
      [fieldContentType]: null,
    });
    if (idInput && this.elementRef.nativeElement.querySelector('#' + idInput)) {
      this.elementRef.nativeElement.querySelector('#' + idInput).value = null;
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const request = this.createFromForm();
    if (request.id !== undefined) {
      this.subscribeToSaveResponse(this.requestService.update(request));
    } else {
      this.subscribeToSaveResponse(this.requestService.create(request));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IRequest>>): void {
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

  protected updateForm(request: IRequest): void {
    this.editForm.patchValue({
      id: request.id,
      name: request.name,
      sex: request.sex,
      profession: request.profession,
      cwzone: request.cwzone,
      cwworeda: request.cwworeda,
      cwfacility: request.cwfacility,
      firstchoice: request.firstchoice,
      code1: request.code1,
      secondchoice: request.secondchoice,
      code2: request.code2,
      thirdchoice: request.thirdchoice,
      code3: request.code3,
      expryear: request.expryear,
      exprmonth: request.exprmonth,
      exprday: request.exprday,
      spexpryear: request.spexpryear,
      spexprmonth: request.spexprmonth,
      spexprday: request.spexprday,
      file: request.file,
      fileContentType: request.fileContentType,
    });
  }

  protected createFromForm(): IRequest {
    return {
      ...new Request(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      sex: this.editForm.get(['sex'])!.value,
      profession: this.editForm.get(['profession'])!.value,
      cwzone: this.editForm.get(['cwzone'])!.value,
      cwworeda: this.editForm.get(['cwworeda'])!.value,
      cwfacility: this.editForm.get(['cwfacility'])!.value,
      firstchoice: this.editForm.get(['firstchoice'])!.value,
      code1: this.editForm.get(['code1'])!.value,
      secondchoice: this.editForm.get(['secondchoice'])!.value,
      code2: this.editForm.get(['code2'])!.value,
      thirdchoice: this.editForm.get(['thirdchoice'])!.value,
      code3: this.editForm.get(['code3'])!.value,
      expryear: this.editForm.get(['expryear'])!.value,
      exprmonth: this.editForm.get(['exprmonth'])!.value,
      exprday: this.editForm.get(['exprday'])!.value,
      spexpryear: this.editForm.get(['spexpryear'])!.value,
      spexprmonth: this.editForm.get(['spexprmonth'])!.value,
      spexprday: this.editForm.get(['spexprday'])!.value,
      fileContentType: this.editForm.get(['fileContentType'])!.value,
      file: this.editForm.get(['file'])!.value,
    };
  }
}

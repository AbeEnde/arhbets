import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IHospital } from '../hospital.model';
import { HospitalService } from '../service/hospital.service';
import { HospitalDeleteDialogComponent } from '../delete/hospital-delete-dialog.component';

@Component({
  selector: 'jhi-hospital',
  templateUrl: './hospital.component.html',
})
export class HospitalComponent implements OnInit {
  hospitals?: IHospital[];
  isLoading = false;

  constructor(protected hospitalService: HospitalService, protected modalService: NgbModal) {}

  loadAll(): void {
    this.isLoading = true;

    this.hospitalService.query().subscribe({
      next: (res: HttpResponse<IHospital[]>) => {
        this.isLoading = false;
        this.hospitals = res.body ?? [];
      },
      error: () => {
        this.isLoading = false;
      },
    });
  }

  ngOnInit(): void {
    this.loadAll();
  }

  trackId(_index: number, item: IHospital): number {
    return item.id!;
  }

  delete(hospital: IHospital): void {
    const modalRef = this.modalService.open(HospitalDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.hospital = hospital;
    // unsubscribe not needed because closed completes on modal close
    modalRef.closed.subscribe(reason => {
      if (reason === 'deleted') {
        this.loadAll();
      }
    });
  }
}

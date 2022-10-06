import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'jhi-department',
  templateUrl: './department.component.html',
  styleUrls: ['./department.component.scss']
})
export class DepartmentComponent implements OnInit {

  form:any;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private modalService: NgbModal,
    // private reqServ: RequestService,

    // eslint-disable-next-line @typescript-eslint/no-unused-vars
   
  ) {
    this.form = new FormGroup({
      depID: new FormControl('', Validators.compose([Validators.required, Validators.pattern('0-9')])),
      depName: new FormControl(
        '',
        Validators.compose([
          Validators.required,
          // eslint-disable-next-line no-useless-escape
          // Validators.min(100000),
        ])
      ),
      available: new FormControl('', Validators.required),

      relesed: new FormControl('', Validators.required),

      assigned: new FormControl('', Validators.required),

      hcode: new FormControl('', Validators.required),


      }) }

  ngOnInit(): void {
    const formValues = sessionStorage.getItem('frm');

    if (formValues) {
      this.applyFormValues(this.form, JSON.parse(formValues));
    }

    this.form.valueChanges
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      .subscribe((frm: any) => {
        sessionStorage.setItem('frm', JSON.stringify(frm));
      });

// eslint-disable-next-line no-console
console.log(this.form.value);
  }

  private applyFormValues(group: any, formValues: any):any {
    Object.keys(formValues).forEach(key => {
      const formControl = <FormControl>group.controls[key];

      if (formControl instanceof FormGroup) {
        this.applyFormValues(formControl, formValues[key]);
      } else {
        formControl.setValue(formValues[key]);
      }
    });
  }
}

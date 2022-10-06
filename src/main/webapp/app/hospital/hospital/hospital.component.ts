import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators,ReactiveFormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { HospitalService } from './hospital.service';
// mport { RequestService } from './request.service';

@Component({
  selector: 'jhi-hospital',
  templateUrl: './hospital.component.html',
  styleUrls: ['./hospital.component.scss']
})
export class HospitalComponent implements OnInit {
form:any;
  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private modalService: NgbModal,
    private reqServ: HospitalService,

    // eslint-disable-next-line @typescript-eslint/no-unused-vars
   
  ) {
    this.form = new FormGroup({

      hospitalName: new FormControl(
        '',
        Validators.compose([
          Validators.required,
          Validators.pattern("^[A-Za-z]*$")
          // eslint-disable-next-line no-useless-escape
          // Validators.min(100000),
        ])
      ),
      code: new FormControl('',
      Validators.compose([
         Validators.required,
         Validators.pattern("^[0-9]*$")])),

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

  passFormData():void {
    this.reqServ.sendHosp(this.form.value).subscribe((reslt)=>{
      // eslint-disable-next-line no-console
      console.log(reslt);
      
    })
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

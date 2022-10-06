import { JsonPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { choiceService } from './choice.service';

@Component({
  selector: 'jhi-choice',
  templateUrl: './choice.component.html',
  styleUrls: ['./choice.component.scss']
})
export class ChoiceComponent implements OnInit {
form: FormGroup;
hospital:any = [];
reqData:any;
parsedreq:any
  constructor(private choiceServise: choiceService) { 
    this.form =new FormGroup({
      hos: new FormControl('',Validators.compose([Validators.required,Validators.pattern("^[0-9]*$")])),
      hospitalName: new FormControl('',Validators.compose([Validators.required,Validators.pattern("^[0-9]*$")])),
      empChoic: new FormControl('',Validators.required),
      profession: new FormControl('',Validators.required)

    })
  }

  ngOnInit(): void {
    this.choiceServise.getHosp().subscribe((res:any)=>{
      this.hospital = res.body;
      // eslint-disable-next-line no-console
       console.log(res);
    })

    this.form.valueChanges
    .subscribe((frm: any) => {
      sessionStorage.setItem('chic', JSON.stringify(frm));
    });

    const formValues = sessionStorage.getItem('chic');
    this.reqData = sessionStorage.getItem('frm');
    this.parsedreq = JSON.parse(this.reqData);

    if (formValues) {
      this.applyFormValues(this.form, JSON.parse(formValues));
    }
   
  
  }



  save():any{
    this.choiceServise.savechoice(this.form.value).subscribe((result:any)=>{
// eslint-disable-next-line no-console
console.log(result);
    })
  }

  validateHosiptal():any{
  
    for(const hosp of this.hospital){
      if(hosp.code === Number(this.form.value.hos) ){
        this.form.patchValue({
          hospitalName: hosp.hospitalName
        })
      
      }
     
    }
     
  }

  fillUserNameNprof():any{
    this.form.patchValue({
     
      profession: this.parsedreq.profession,
      empChoic: this.parsedreq.userName,
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

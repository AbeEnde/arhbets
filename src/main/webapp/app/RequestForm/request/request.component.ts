import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators,ReactiveFormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { RequestService } from './request.service';

@Component({
  selector: 'jhi-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.scss']
})
export class RequestComponent {

  form: FormGroup;
  isShown = false ; 
  fileToUpload: File | null = null;
  http: any;
  apiEndPoint: any;
  selectedFile: File | undefined;
  formObject:any;

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private modalService: NgbModal,
    private reqServ: RequestService,

   
  ) {
    this.form = new FormGroup({
      userName: new FormControl('', Validators.compose([Validators.required])),


      name: new FormControl('', Validators.compose([Validators.required, Validators.pattern("^[A-Za-z]*$")])),
      sex: new FormControl(
        '',
        Validators.compose([
          Validators.required,
          
        ])
      ),
      profession: new FormControl('', Validators.required),
     zone: new FormControl(
        '',
        Validators.compose([Validators.required, Validators.pattern("^[A-Za-z]*$")])
      ),
      woreda: new FormControl(
        '',
        Validators.compose([Validators.required, Validators.pattern("^[A-Za-z]*$")])
      ),
      facility: new FormControl(
        '',
        Validators.compose([Validators.required, Validators.pattern("^[A-Za-z]*$")])
      ),

      exprYear: new FormControl(
        '',
        Validators.compose([Validators.required, Validators.pattern("^[0-9]*$")])
      ),

     expMon: new FormControl('',Validators.compose([Validators.required])),

      expDay: new FormControl(
        '',
       Validators.compose([Validators.required, Validators.pattern("^[0-9]*$")])
      ),

      spexpryear: new FormControl(
        '',
        Validators.compose([Validators.required, Validators.pattern("^[0-9]*$")])
      ),

      spexprmonth: new FormControl(
        '',
        Validators.compose([Validators.required, Validators.pattern("^[0-9]*$")])
      ),

      spexprday: new FormControl(
        '',
        Validators.compose([Validators.required, Validators.pattern("^[0-9]*$")])
      ),

      fileContentType: new FormControl(
        '',
      ),
     
    });
  }

  ngOnInit(): void {
   

    this.form.valueChanges
      // eslint-disable-next-line @typescript-eslint/no-unused-vars
      .subscribe((frm: any) => {
        sessionStorage.setItem('frm', JSON.stringify(frm));
      });

      const formValues = sessionStorage.getItem('frm');

      if (formValues) {
        this.applyFormValues(this.form, JSON.parse(formValues));
      }

      
// eslint-disable-next-line no-console
console.log(this.form.value);
this.formObject = this.form.value;
  } 

 passFormData():void {
this.reqServ.sendReq(this.formObject).subscribe((reslt)=>{
  // eslint-disable-next-line no-console
  console.log(reslt);
  
})
 }

 toggleShow(): void {

  this.isShown = ! this.isShown;
  
  }

  public onFileChanged(event:any) : any{
    //Select File
    this.selectedFile = event.target.files[0];
  }

  holdData():any{
    this.reqServ.hold(this.form.value);
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
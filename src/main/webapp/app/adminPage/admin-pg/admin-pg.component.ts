import { Component, OnInit } from '@angular/core';
import { AdminPgService } from './admin-pg.service';
import { DomSanitizer } from '@angular/platform-browser';
import { result } from '../result.model'
import {  FormControl, FormGroup,  } from '@angular/forms';



@Component({
  selector: 'jhi-admin-pg',
  templateUrl: './admin-pg.component.html',
  styleUrls: ['./admin-pg.component.scss']
})
export class AdminPgComponent implements OnInit {
  
  form: any;
  enableEdit = false;
  enableEditIndex = null;

  body: result = {
    expValue: '',
    PlaceV: '',
    genderV: '',
    spauseV: '',
    medicalV: '',
    totalV: '',
    resultV: ''
  };
  bodyArray:any=[];
temp:any;
 finalData:any=[];
 lastData:any=[];
  resultData: any = [];
  mergData: any = [];
  mapped: any;
  data: any;
  dat!:any
  total: any ;
  constructor(
    private adminPgService: AdminPgService,
    private sanitizer: DomSanitizer,


  ) {

  }


  ngOnInit(): void {

    this.adminPgService.getresult().subscribe((reslt)=>{
      this.bodyArray = [...reslt.body];
    
       // eslint-disable-next-line no-console
       console.log(reslt.body);
    })

    this.adminPgService.getAllData().subscribe((res) => {
     
     for (let i =0; i<res.body.length;i++) {
      const  kt = { ...res.body[i], ...this.bodyArray[i] };
        this.mergData.push(kt);
    }
    
        localStorage.setItem('data',JSON.stringify(this.mergData))

         this.dat = localStorage.getItem('data');
          JSON.parse(this.dat );
        this.finalData .push(JSON.parse(this.dat));
    
        for(const item of this.finalData[0]){
          this.lastData.push(item);
        }
      
        // eslint-disable-next-line no-console
        console.log(res.body);
     
    })

        // eslint-disable-next-line no-console
      // console.log(this.mergData);

      // if (this.lastData) {
      //   this.applyFormValues(this.form, (this.lastData));
      // }

  }


  enableEditMethod(): any {
    this.enableEdit = true;
  }

  splite(): any {
    for (const item of this.lastData) {
      const obj: any = {}
      for (const k in item) {
        if (k === "id") {
          obj[k] = item[k];

        }
     
        else if (k === "code") {
          obj[k] = item[k];

        }

        else if (k === "userName") {
          obj[k] = item[k];

        }

        else if (k === "expValue") {
          obj[k] = item[k];

        }
        else if (k === "PlaceV") {
          obj[k] = item[k];
        }
        else if (k === "genderV") {
          obj[k] = item[k];
        }
        else if (k === "spauseV") {
          obj[k] = item[k];
        }
        else if (k === "medicalV") {
          obj[k] = item[k];
        }
        else if (k === "totalV") {
          obj[k] = item[k];
        }
        else {
          // // eslint-disable-next-line no-console
          // console.log('nothing');
        }




      }
      this.resultData.push(obj)


    }
    // eslint-disable-next-line no-console
    // console.log(this.resultData);
    return this.resultData;
  }

// getTotal(i:number,data:any):any{


// this.total = Number(this.total) + Number(data)

// this.lastData[i].totalV = this.total;

// // eslint-disable-next-line no-console
// console.log(this.total)
// }

  saveResult(): any {
    for (const resultd of this.splite()) {
      this.adminPgService.saveRes(resultd).subscribe((resl) => {
         // eslint-disable-next-line no-console
          console.log(resl);
      })

    }


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

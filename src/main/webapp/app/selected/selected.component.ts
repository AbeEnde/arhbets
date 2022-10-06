import { Component, OnInit } from '@angular/core';
import { SelectedService } from './selected.service';

@Component({
  selector: 'jhi-selected',
  templateUrl: './selected.component.html',
  styleUrls: ['./selected.component.scss']
})
export class SelectedComponent implements OnInit {
data:any ;
  constructor(
    private selctedService:SelectedService ) { }

  ngOnInit(): void {


   this.selctedService.getSelected().subscribe((rse:any)=>{
    this.data=rse.body;
  // eslint-disable-next-line no-console
  console.log(rse);
      

   })

  }

}

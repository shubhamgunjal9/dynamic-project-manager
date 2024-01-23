import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ApiService } from '../api.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-project',
  templateUrl: './create-project.component.html',
  styleUrls: ['./create-project.component.css']
})
export class CreateProjectComponent {
  constructor(private api:ApiService,private router:Router){}

  public dateValidAlert: string = 'Date required';

  public projectForm: any = new FormGroup({
    'proname': new FormControl('',Validators.required),
    'projectReason': new FormControl('Business'),
    'projectType': new FormControl('Internal'),
    'projectDivision': new FormControl('Filters'),
    'projectCategory': new FormControl('Quality A'),
    'projectPriority': new FormControl('Low'),
    'projectDept': new FormControl('Strategy'),
    'prostartdate': new FormControl('', Validators.required),
    'proenddate': new FormControl('', Validators.required),
    'projectLocation': new FormControl('Pune'),
    'projectStatus': new FormControl('Registerd'),
  });


  public createBackendJson(): any {
    const backendJson = {
      categoryName: this.projectForm.value.projectCategory,
      deptName: this.projectForm.value.projectDept,
      divisionName: this.projectForm.value.projectDivision,
      projectName: this.projectForm.value.proname,
      projectStartDate: new Date(this.projectForm.value.prostartdate),
      projectEndDate: new Date(this.projectForm.value.proenddate),
      locationName: this.projectForm.value.projectLocation,
      priorityName: this.projectForm.value.projectPriority,
      reasonName: this.projectForm.value.projectReason,
      statusName: this.projectForm.value.projectStatus,
      typeName: this.projectForm.value.projectType,
    };

    console.log(backendJson);
    
    return backendJson;
  }





  public validateField = {'theme': false, 'sdate': false, 'edate': false,}

  public projectregister(): void {
    if(this.projectForm.value.proname == ''){
      this.validateField.theme = true;
    }
    if(this.projectForm.value.prostartdate == ''){
      this.validateField.sdate = true;
    }
    if(this.projectForm.value.prostartdate == ''){
      this.validateField.edate = true;
    }

    if(this.projectForm.valid) {

      console.log(this.projectForm.value)

      if(this.projectForm.value.prostartdate < this.projectForm.value.proenddate){

        const backendJson = this.createBackendJson();
        this.api.add_project(backendJson).subscribe((res: any) => { 
          this.router.navigate(['/project_list']);
        })
      } else {
        this.dateValidAlert = 'End date is smaller than the start date';
        this.validateField.edate = true;
      }
    }
  }

  public removeinputalert(): void {
    this.validateField.theme = false;
    this.validateField.sdate = false;
    this.validateField.edate = false;
  }
}

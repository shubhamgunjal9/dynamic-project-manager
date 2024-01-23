import { Component, OnInit } from '@angular/core';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {
  constructor(private api:ApiService){}
  public passwordType: string = 'password';
  public projectData: any[] = [];

  public currentPage: number = 1;
  public itemPerPage: number = 7;
  public totalProduct: number = 0;

  ngOnInit() {
    this.getProjects();
  }

  public getProjects(): void {
    this.api.project_lists().subscribe((res: any) => {

      this.projectData = res;
      console.log(this.projectData);
      console.log(res);
      this.totalProduct = res.length;
    })
  }



  searchProjects(event: any): void {
    const searchTerm = (event?.target?.value || '').trim();
    if (!searchTerm) {
      this.getProjects();
      return;
    }
    this.projectData = this.projectData.filter(project =>
      project.proname.toLowerCase().includes(searchTerm.toLowerCase() || searchTerm.toUppercase())||
      project.projectReason.prname.toLowerCase().includes(searchTerm.toLowerCase() || searchTerm.toUppercase()) ||
      project.projectDivision.pdivname.toLowerCase().includes(searchTerm.toLowerCase() || searchTerm.toUppercase()) ||
      project.projectCategory.pcname.toLowerCase().includes(searchTerm.toLowerCase() || searchTerm.toUppercase()) ||
      project.projectDept.pdname.toLowerCase().includes(searchTerm.toLowerCase() || searchTerm.toUppercase()) ||
      project.projectLocation.plname.toLowerCase().includes(searchTerm.toLowerCase() || searchTerm.toUppercase()) ||
      project.projectStatus.psname.toLowerCase().includes(searchTerm.toLowerCase() || searchTerm.toUppercase()) ||
      project.projectPriority.ppname.toLowerCase().includes(searchTerm.toLowerCase() || searchTerm.toUppercase())

    );
    this.totalProduct = this.projectData.length;
  }







public orderProjectList(event: any): void {
  const selectedValue = event.target.value;
  console.log(selectedValue);

  this.api.order_project_list(selectedValue).subscribe((res: any) => {
    this.projectData = res;
    this.totalProduct = res.length;
  });
}


  public changestatus(id:number,obj:any): void {

    console.log("psid: "+id)
    this.api.change_status(id,obj).subscribe((res: any)=>{
      console.log(id);

      console.log(obj);
      this.getProjects();
     // this.ngOnInit();

    })
  }

}




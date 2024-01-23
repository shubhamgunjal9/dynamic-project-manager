import { Component, Input} from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent{
  @Input() page:any;
  
  constructor(private router:Router){}

  public logout(): void{
    localStorage.clear();
    this.router.navigate(['/']);
  }
}

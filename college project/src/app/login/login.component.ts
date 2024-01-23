import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from './../api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  constructor(private router: Router, private apiService: ApiService) {}

  public passwordType: string = 'password';

  loginForm: any = new FormGroup({
    'email': new FormControl('', [Validators.required, Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$')]),
    'password': new FormControl('', Validators.required),
  });

  public validateField: any = { email: false, password: false, invalidLogin: false };

  public userlogin(): void {
    const email = this.loginForm.value.email;
    const password = this.loginForm.value.password;

    this.validateField.email = email === '';
    this.validateField.password = password === '';

    this.apiService.login_user(email, password).subscribe(
      (response) => {
        const isValidCredentials = this.checkCredentials(email, password, response);

        if (isValidCredentials) {
          console.log('Login successful');
          this.router.navigate(['dashboard']);
        } else {
          this.handleFailedLogin();
        }
      },
      (error) => {
        console.error('Error:', error);
        this.handleFailedLogin();
      }
    );
  }

  private handleFailedLogin(): void {
    this.validateField.invalidLogin = true;
    console.log('Login failed');
    console.log('validateField.invalidLogin:', this.validateField.invalidLogin);
  }

  private checkCredentials(email: string, password: string, response: any): boolean {
    const userCredential = {
      "uemail": response.uemail,
      "upass": response.upass,
      "role": response.role
    };

    const hardcodedEmail = userCredential.uemail;
    const hardcodedPassword = userCredential.upass;

    return email === hardcodedEmail && password === hardcodedPassword;
  }

  public passwordShowHide(): void {
    this.passwordType = this.passwordType === 'password' ? 'text' : 'password';
  }
  public registration() : void {
    this.router.navigate(['registration']);
  }
}

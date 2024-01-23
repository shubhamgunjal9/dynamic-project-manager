// registration.component.ts
import { HttpClient } from '@angular/common/http';
import { ApiService } from './../api.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  RegisterForm: any = FormGroup;
  constructor(private fb: FormBuilder, private apiService: ApiService, private http:Router) { }
  public passwordType: string = 'password';
  ngOnInit(): void {
    this.RegisterForm = this.fb.group({
      uemail: ['', [Validators.required, Validators.pattern('^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+.[a-zA-Z]{2,}$')]],
      upass: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    });
  }

  // onSubmit() {
  //   console.log("hello");

  //   if (this.RegisterForm.valid) {
  //     const userData = {
  //       "uemail": this.RegisterForm.value.uemail,
  //       "upass": this.RegisterForm.value.upass
  //     };

  //     this.apiService.save_user(userData).subscribe(
  //       (response) => {
  //         console.log('Registration successful:', response);
  //       }
  //     );
  //   }
  // }
  onSubmit() {
    console.log("hello");

    if (this.RegisterForm.valid) {
      const userData = {
        "uemail": this.RegisterForm.value.uemail,
        "upass": this.RegisterForm.value.upass
      };

      this.apiService.save_user(userData).subscribe(
        (response: string) => {
          console.log('Server Response:', response);

          // Handle the text response as needed
          if (response === 'saved') {
            console.log('Registration successful!');

          } else {
            console.log('Unexpected response:', response);
          }
        }
      );
    }
  }



  get uemail() {
    return this.RegisterForm.get('uemail');
  }

  get upass() {
    return this.RegisterForm.get('upass');
  }

  get confirmPassword() {
    return this.RegisterForm.get('confirmPassword');
  }

  public passwordShowHide(): void {
    this.passwordType = this.passwordType === 'password' ? 'text' : 'password';
  }

  public passwordIsSame(): void {
    const upass1: string = this.RegisterForm.value.upass;
    const confirmPassword1: string = this.RegisterForm.value.confirmPassword;

    if (upass1 !== confirmPassword1) {
      alert("Passwords should be the same.");
    }
  }

  public reDirect() {
    alert("user saved successfully...");
    this.http.navigate(['/']);
  }
}

import { Component } from '@angular/core';
import { User } from 'src/app/types/user';
import { RegisterService } from './register.service';
import { AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {

  private user: User = {
    username: ' ',
    password: ' ',
    confirmPassword: ' '
  };

  validation_messages = {
    'username': [
      { type: 'required', message: 'Username is required' },
      { type: 'minlength', message: 'Username must be at least 1 characters long' }
    ],
    'email': [
      { type: 'required', message: 'Email is required' },
      { type: 'email', message: 'Enter a valid email' }
    ],
    'confirmPassword': [
      { type: 'required', message: 'Confirm password is required' },
      { type: 'areEqual', message: 'Password mismatch' }
    ],
    'password': [
      { type: 'required', message: 'Password is required' },
      { type: 'minlength', message: 'Password must be at least 1 characters long' },
      { type: 'pattern', message: 'Your password must contain at least one uppercase, one lowercase, and one number' }
    ]
  }

  constructor(
    private registerService: RegisterService,
    private router: Router
  ) { }

  registrationForm = new FormGroup({
    username: new FormControl('', [Validators.required, Validators.minLength(1)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(1)]),
    confirmPassword: new FormControl('', [Validators.required, Validators.minLength(1)])

  });

  get username() {
    return this.registrationForm.get('username');
  }

  get email() {
    return this.registrationForm.get('email');
  }

  get password() {
    return this.registrationForm.get('password');
  }

  get confirmPassword() {
    return this.registrationForm.get('confirmPassword');
  }

  register() {
    const user = this.user;
    const value = this.registrationForm.value;
    user.username = value.username ?? '';
    user.email = value.email ?? '';
    user.password = value.password ?? '';
    user.confirmPassword = value.confirmPassword ?? '';
debugger
    this.registerService.register(user).subscribe(user => {
      if (user.id) {
        this.router.navigate(['login']);
      }
    });

  }

  areEqual(password: any): ValidatorFn {
    return (control: AbstractControl): ValidationErrors | null => {
      const forbidden = control.value === password;
      return forbidden ? {forbiddenName: {value: control.value}} : null;
    };
  }

}

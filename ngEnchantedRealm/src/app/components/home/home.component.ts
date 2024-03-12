import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { User } from '../../models/user';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {
  user: User = new User();
  constructor(private auth: AuthService, private router: Router) {}

  ngOnInit(): void {
    console.log('ngIit');
    this.getUser();
  }

  getUser() {
    if (this.isLoggedIn()) {
      this.auth.getLoggedInUser().subscribe({
        next: (result) => {
          this.user = result;
        },
        error: (err) => {
          console.log(err);
        },
      });
    }
  }

  isApplicant() {
    if (this.isLoggedIn()) {
      if (this.user.role === '') {
        this.getUser();
      }
      if (this.user.role === 'Applicant') {
        return true;
      }
    }
    return false;
  }

  isCompany() {
    if (this.isLoggedIn()) {
      if (this.user.role === '') {
        this.getUser();
      }
      if (this.user.role === 'Company') {
        return true;
      }
    }
    return false;
  }

  isLoggedIn() {
    return this.auth.checkLogin();
  }

  isMessageSent: boolean = false;
  formFullName: string = '';
  formEmail: string = '';
  formPhoneNumber: string = '';
  formSubject: string = '';
  formMessage: string = '';

  sendMessage() {
    this.isMessageSent = true;
    this.formFullName = '';
    this.formEmail = '';
    this.formPhoneNumber = '';
    this.formSubject = '';
    this.formMessage = '';
    setTimeout(() => {
      this.isMessageSent = false;
    }, 5000);
  }
}

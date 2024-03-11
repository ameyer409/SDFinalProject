import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { User } from '../../models/user';
import { Applicant } from '../../models/applicant';
import { ApplicantService } from '../../services/applicant.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-applicant-profile',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './applicant-profile.component.html',
  styleUrl: './applicant-profile.component.css',
})
export class ApplicantProfileComponent implements OnInit {
  user: User = new User();
  applicant: Applicant = new Applicant();
  editUser: User | null = null;
  editApplicant: Applicant | null = null;
  confirmPassword: string = '';

  constructor(
    private auth: AuthService,
    private applicantService: ApplicantService
  ) {}

  ngOnInit(): void {
    this.getUser();
    this.getApplicant();
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

  getApplicant() {
    this.applicantService.getApplicant().subscribe({
      next: (result) => {
        this.applicant = result;
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  isLoggedIn() {
    return this.auth.checkLogin();
  }

  editUserCredentials() {
    if (this.editUser == null) {
      this.editUser = Object.assign({}, this.user);
      this.editUser.password = ''
    } else {
      console.log(this.editUser.password);
      console.log(this.confirmPassword);
      this.editUser = null;
    }
  }

  editApplicantCredentials() {
    if (this.editApplicant == null) {
      this.editApplicant = Object.assign({}, this.applicant);
    } else {
      this.editApplicant = null;
    }
  }

  updateApplicantInfo(applicant: Applicant) {
    this.applicantService.update(applicant).subscribe({
      next: (result) => {
        this.applicant = result;
        this.editApplicantCredentials();
      },
      error: (err) => {
        console.log(err);
      }
    })

  }

}

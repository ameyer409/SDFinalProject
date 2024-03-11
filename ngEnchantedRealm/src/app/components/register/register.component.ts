import { CompanyService } from './../../services/company.service';
import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { User } from '../../models/user';
import { AuthService } from '../../services/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Applicant } from '../../models/applicant';
import { Company } from '../../models/company';
import { ApplicantService } from '../../services/applicant.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule,
    FormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  newUser: User = new User();
  newApplicant: Applicant = new Applicant();
  newCompany: Company = new Company();
  confirmPassword: string = '';

  constructor(
    private authService: AuthService,
    private router: Router,
    private applicantService: ApplicantService,
    private companyService: CompanyService
    ) {}

  register(newUser: User) {
    if(newUser.password === this.confirmPassword){
      this.authService.register(newUser).subscribe({
        next: (result) => {
          this.authService.login(newUser.username, newUser.password).subscribe({
            next: (result) => {
              if(result.role === 'Company'){
                this.newCompany.user = result
                // this.newCompany.address.id = 1;
                this.companyService.create(this.newCompany).subscribe({
                  next: (result) => {
                    this.router.navigateByUrl('companyProfile')
                  },
                  error: (err) => {
                    console.log(err);
                    this.router.navigateByUrl('notfound');
                  }
                })
              }
              else if(result.role === 'Applicant') {
                this.newApplicant.user = result;
                this.applicantService.create(this.newApplicant).subscribe({
                  next: (result) => {
                    console.log(result);
                    this.router.navigateByUrl('applicantProfile')
                  },
                  error: (err) => {
                    console.log(err);
                    this.router.navigateByUrl('notfound');
                  }
                });
              }
            },
            error: (err) => {
              console.log(err);
              this.router.navigateByUrl('notfound');
            }
          }

          );
        },
        error: (err) => {
          console.log(err);
        }
      });
    }
    else {
      console.log(newUser);
    }
  }
}

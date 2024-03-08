import { TestimonialComponent } from './components/testimonial/testimonial.component';
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AuthService } from './services/auth.service';
import { JobPostingComponent } from './components/job-posting/job-posting.component';
import { RegisterComponent } from './components/register/register.component';
import { LogInComponent } from './components/log-in/log-in.component';
import { AboutComponent } from './components/about/about.component';
import { JobDetailComponent } from './components/job-detail/job-detail.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';

import { ApplicantProfileComponent } from './components/applicant-profile/applicant-profile.component';
import { CompanyProfileComponent } from './components/company-profile/company-profile.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    JobPostingComponent,
    RegisterComponent,
    LogInComponent,
    TestimonialComponent,
    AboutComponent,
    JobDetailComponent,
    NavBarComponent,
    ApplicantProfileComponent,
    CompanyProfileComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'ngEnchantedRealm';

  constructor(private auth: AuthService) {}

  ngOnInit() {
    // this.tempTestDeleteMeLater(); // DELETE LATER!!!
  }

  tempTestDeleteMeLater() {
    this.auth.login('admin', 'test').subscribe({
      // change username to match DB
      next: (data) => {
        console.log('Logged in:');
        console.log(data);
      },
      error: (fail) => {
        console.error('Error authenticating:');
        console.error(fail);
      },
    });
  }
}

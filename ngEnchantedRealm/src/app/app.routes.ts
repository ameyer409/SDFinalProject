import { Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { AboutComponent } from './components/about/about.component';
import { ApplicantProfileComponent } from './components/applicant-profile/applicant-profile.component';
import { CompanyProfileComponent } from './components/company-profile/company-profile.component';
import { JobPostingComponent } from './components/job-posting/job-posting.component';
import { TestimonialComponent } from './components/testimonial/testimonial.component';
import { LogInComponent } from './components/log-in/log-in.component';
import { RegisterComponent } from './components/register/register.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { JobDetailComponent } from './components/job-detail/job-detail.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'applicantProfile', component: ApplicantProfileComponent },
  { path: 'companyProfile', component: CompanyProfileComponent },
  { path: 'viewAllJobs', component: JobPostingComponent },
  { path: 'testimonial', component: TestimonialComponent },
  { path: 'logIn', component: LogInComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'jobDetail', component: JobDetailComponent },
  { path: '**', component: NotFoundComponent }, //page not found route
];

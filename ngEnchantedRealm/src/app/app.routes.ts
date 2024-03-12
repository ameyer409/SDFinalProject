import { JobDetailCompanyViewComponent } from './components/job-detail-company-view/job-detail-company-view.component';
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
import { JobDetailApplicantViewComponent } from './components/job-detail-applicant-view/job-detail-applicant-view.component';
import { JobPostingCompanyViewComponent } from './components/job-posting-company-view/job-posting-company-view.component';
import { JobDetailGuestViewComponent } from './components/job-detail-guest-view/job-detail-guest-view.component';

export const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: 'home' },
  { path: 'home', component: HomeComponent },
  { path: 'about', component: AboutComponent },
  { path: 'applicantProfile', component: ApplicantProfileComponent },
  { path: 'companyProfile', component: CompanyProfileComponent },
  { path: 'companyProfile/:id', component: CompanyProfileComponent },
  { path: 'viewAllJobsApplicant', component: JobPostingComponent },
  { path: 'viewAllJobsCompany', component: JobPostingCompanyViewComponent },

  { path: 'testimonial', component: TestimonialComponent },
  { path: 'logIn', component: LogInComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'jobDetailApplicantView',
    component: JobDetailApplicantViewComponent,
  },
  {
    path: 'jobDetailApplicantView/:id',
    component: JobDetailApplicantViewComponent
  },
  {
    path: 'jobDetailCompanyView',
    component: JobDetailCompanyViewComponent,
  },
  {
    path: 'jobDetailGuestView',
    component: JobDetailGuestViewComponent,
  },
  { path: '**', component: NotFoundComponent }, //page not found route
];

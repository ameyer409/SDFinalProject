import { Application } from './../../models/application';
import { ApplicationService } from './../../services/application.service';
import { Company } from './../../models/company';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { JobpostingService } from '../../services/jobposting.service';
import { Jobposting } from '../../models/jobposting';
import { CompanyService } from '../../services/company.service';
import { ApplicantService } from '../../services/applicant.service';
import { Applicant } from '../../models/applicant';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-job-detail-applicant-view',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterLink
  ],
  templateUrl: './job-detail-applicant-view.component.html',
  styleUrl: './job-detail-applicant-view.component.css'
})
export class JobDetailApplicantViewComponent implements OnInit{

  job: Jobposting | null = null;
  apps: Application[] = [];
  currentApplicant: Applicant | null = null;
  currentApplication: Application | null = null;
  newApplication: Application = new Application();

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private jobService: JobpostingService,
    private applicantService: ApplicantService,
    private appService: ApplicationService
  ){}

  ngOnInit(): void {
    this.getJobPosting();
  }

  checkApplicationStatus() {
    this.currentApplication = null;
    this.apps.forEach(app => {
      if(app.applicant.id === this.currentApplicant?.id) {
        this.currentApplication = app;
      }
    });
  }

  reload() {
    if(this.job != null) {
      this.getApps(this.job?.id);
    }
  }

  getJobPosting() {
    this.activatedRoute.paramMap.subscribe({
      next: (params) => {
        let jobIdStr = params.get('id');
        if (jobIdStr != null) {
          let jobId = parseInt(jobIdStr);
          if (!isNaN(jobId)) {
            this.jobService.show(jobId).subscribe({
              next: (jobPost) => {
                  this.job = jobPost;
                  this.getApps(this.job.id);
              },
              error: (err) => {
                console.error('JobDetailAplicantViewComponent.ngOnInt(): error retreiving JobPosting:');
                console.error(err);
                this.router.navigateByUrl('notfound');
              },
            });
          }
          else{
            this.router.navigateByUrl('notfound');
          }
        }
      }
    })
  }

  getApplicant() {
    this.applicantService.getApplicant().subscribe({
      next: (result) => {
        this.currentApplicant = result;
        this.checkApplicationStatus();
        // this.applicationService.index().subscribe({
        //   next: (result) => {
        //     this.apps = result;
        //   },
        //   error: (err) => {
        //     console.log(err);
        //   }
        // })
      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  getApps(jobId: number) {
    this.jobService.findApplications(jobId).subscribe({
      next: (apps) => {
        this.apps = apps;
        this.getApplicant();
      },
      error: (err) => {
        console.log('JobDetailApplicantView.getApps(): error retrieving applications: ');
        console.log(err);
      }
    })
  }

  removeApplication(id: number) {
    this.appService.destroy(id).subscribe({
      next: () => {
        this.reload();
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  applyToJobPosting() {
    if(this.job != null) {
      this.newApplication.jobPosting = this.job
    }
    if (this.currentApplicant != null) {
      this.newApplication.applicant = this.currentApplicant
      this.appService.create(this.newApplication.applicant.id, this.newApplication).subscribe({
        next: (result) => {
          this.reload();
        },
        error: (err) => {
          console.log(err);
        }
      })
    }
  }

}

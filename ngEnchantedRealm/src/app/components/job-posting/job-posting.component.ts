import { Jobposting } from './../../models/jobposting';
import { ApplicantService } from './../../services/applicant.service';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { JobpostingService } from '../../services/jobposting.service';
import { Application } from '../../models/application';
import { Applicant } from '../../models/applicant';
import { ApplicationService } from '../../services/application.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-job-posting',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule,
    FormsModule
  ],
  templateUrl: './job-posting.component.html',
  styleUrl: './job-posting.component.css',
})
export class JobPostingComponent implements OnInit{

  jobpostings: Jobposting[] = [];
  applicant: Applicant = new Applicant;
  myApplications: Application[] = [];
  haveApplied: boolean = false;

  constructor(
    private router: Router,
    private jobService: JobpostingService,
    private applicantServ: ApplicantService,
    private appService: ApplicationService,
    private auth: AuthService
    ) {}

  ngOnInit(): void {
    this.loadJobPostings();
    if(this.auth.checkLogin()){
      this.getApplicant();
    }
  }

  public loadJobPostings(){
    this.jobService.index().subscribe({
      next: (result) => {
        this.jobpostings = result;
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  getApplicant() {
    this.applicantServ.getApplicant().subscribe({
      next: (result) => {
        this.applicant = result;
        this.getApplications();

      },
      error: (err) => {
        console.log(err);
      },
    });
  }

  getApplications() {
    this.appService.index().subscribe({
      next: (result) => {
        this.myApplications = result;
      },
      error: (err) => {
        console.log(err);
      }
    })
  }

  applyToJobPosting(job: Jobposting) {
    let newApp: Application = new Application;
      newApp.jobPosting = job
      newApp.applicant = this.applicant;

      this.appService.create(newApp.applicant.id, newApp).subscribe({
        next: (result) => {
          this.loadJobPostings();
          this.getApplications();
          // this.router.navigateByUrl('/viewAllJobsApplicant')
        },
        error: (err) => {
          console.log(err);
        }
      })
    }

    appliedTo(job: Jobposting): boolean {
      for(let app of this.myApplications){
        if(app.jobPosting.id == job.id) {
          return true;
        }
      }
      return false;
    }

  public moveToJobDetail() {
    this.router.navigateByUrl('jobDetail');
  }

  public moveToProfile() {
    this.router.navigateByUrl('applicantProfile');
  }
}

import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { JobpostingService } from './../../services/jobposting.service';
import { Component, OnInit } from '@angular/core';
import { Jobposting } from '../../models/jobposting';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-job-detail-company-view',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    RouterLink
  ],
  templateUrl: './job-detail-company-view.component.html',
  styleUrl: './job-detail-company-view.component.css'
})
export class JobDetailCompanyViewComponent implements OnInit {


jobposting: Jobposting | null = null;

editedJobPosting: Jobposting | null = null;

ngOnInit(): void {
  this.getJobPostingDetails();
}


constructor(
  private jobService: JobpostingService,
  private activatedRoute: ActivatedRoute,
  private router: Router,
  private auth: AuthService
  ){};

  getJobPostingDetails() {
    this.activatedRoute.paramMap.subscribe({
      next: (params) => {
        let jobIdStr = params.get('id');
        if (jobIdStr != null) {
          let jobId = parseInt(jobIdStr);
          if (!isNaN(jobId)) {
            this.jobService.show(jobId).subscribe({
              next: (jobPost) => {
                  this.jobposting = jobPost;
              },
              error: (err) => {
                console.error('JobDetailCompanyViewComponent.ngOnInt(): error retreiving JobPosting:');
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

  isLoggedIn() {
    return this.auth.checkLogin();
  }

  editJobPosting() {
    if(this.editedJobPosting == null){
      this.editedJobPosting = Object.assign({}, this.jobposting);
    }
    else {
      this.editedJobPosting = null;
    }
  }

  updateJobPosting(jobPosting: Jobposting) {
    this.jobService.update(jobPosting).subscribe({
      next: (result) => {
        this.jobposting = result;
        this.editJobPosting();
      },
      error: (err) => {
        console.log(err)
      }
    })
  }


}

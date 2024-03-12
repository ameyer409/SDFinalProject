import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from './../../services/auth.service';
import { Component, OnInit } from '@angular/core';
import { JobpostingService } from '../../services/jobposting.service';
import { Jobposting } from '../../models/jobposting';

@Component({
  selector: 'app-job-detail-applicant-view',
  standalone: true,
  imports: [],
  templateUrl: './job-detail-applicant-view.component.html',
  styleUrl: './job-detail-applicant-view.component.css'
})
export class JobDetailApplicantViewComponent implements OnInit{

  job: Jobposting | null = null;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private jobService: JobpostingService
  ){}

  ngOnInit(): void {
    console.log('landing on details page');
    this.activatedRoute.paramMap.subscribe({
      next: (params) => {
        let jobIdStr = params.get('id');
        if (jobIdStr != null) {
          let jobId = parseInt(jobIdStr);
          if (!isNaN(jobId)) {
            this.jobService.show(jobId).subscribe({
              next: (jobPost) => {
                  this.job = jobPost;
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

}

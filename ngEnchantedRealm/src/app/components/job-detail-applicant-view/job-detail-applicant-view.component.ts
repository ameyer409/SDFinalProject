import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-job-detail-applicant-view',
  standalone: true,
  imports: [],
  templateUrl: './job-detail-applicant-view.component.html',
  styleUrl: './job-detail-applicant-view.component.css'
})
export class JobDetailApplicantViewComponent implements OnInit{
  ngOnInit(): void {
    console.log('landing on details page');
  }

}

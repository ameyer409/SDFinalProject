import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-job-posting',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './job-posting.component.html',
  styleUrl: './job-posting.component.css',
})
export class JobPostingComponent {
  constructor(private router: Router) {}
  public moveToJobDetail() {
    this.router.navigateByUrl('jobDetail');
  }

  public moveToProfile() {
    this.router.navigateByUrl('applicantProfile');
  }
}

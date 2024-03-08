import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-job-posting',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './job-posting.component.html',
  styleUrl: './job-posting.component.css',
})
export class JobPostingComponent {}

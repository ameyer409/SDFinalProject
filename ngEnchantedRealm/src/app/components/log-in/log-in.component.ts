import { AuthService } from './../../services/auth.service';
import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { User } from '../../models/user';

@Component({
  selector: 'app-log-in',
  standalone: true,
  imports: [
    RouterLink,
    CommonModule,
    FormsModule
  ],
  templateUrl: './log-in.component.html',
  styleUrl: './log-in.component.css',
})
export class LogInComponent {
  currentUser: User = new User();

  constructor(private auth: AuthService, private router: Router) {}

  login(user: User) {
    this.auth.login(user.username, user.password).subscribe({
      next: (result) => {
          this.router.navigateByUrl('home');
      },
      error: (err) => {
        console.log(err);
      }
    });
  }
}

import { User } from './../../models/user';
import { Component, OnInit } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [RouterLink, CommonModule, FormsModule],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css',
})
export class NavBarComponent implements OnInit{

  user: User = new User();
  constructor(private auth: AuthService, private router: Router){}

  ngOnInit(): void {
    console.log('ngIit')
    this.getUser();
  }

  getUser() {
    if(this.isLoggedIn()){
      this.auth.getLoggedInUser().subscribe({
        next: (result) => {
          this.user = result;
        },
        error: (err) => {

          console.log(err);
        }
      });
    }
  }

  isApplicant() {
    if(this.isLoggedIn()){
      if(this.user.role === ''){
        this.getUser();
      }
      if (this.user.role === 'Applicant') {
        return true;
      }
    }
      return false;
  }

  isCompany() {
    if(this.isLoggedIn()){
      if(this.user.role === ''){
        this.getUser();
      }
      if (this.user.role === 'Company') {
        return true;
      }
    }
      return false;
  }

  isLoggedIn() {
    return this.auth.checkLogin();
  }


  logout() {
    console.log('logout');
    this.auth.logout();
    this.user = new User();
    this.router.navigateByUrl('home');
  }
}

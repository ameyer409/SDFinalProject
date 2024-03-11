import { User } from './../../models/user';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Application } from '../../models/application';
import { Jobposting } from '../../models/jobposting';
import { Company } from '../../models/company';
import { CompanyService } from '../../services/company.service';


@Component({
  selector: 'app-company-profile',
  standalone: true,
  imports: [FormsModule,
            CommonModule],
  templateUrl: './company-profile.component.html',
  styleUrl: './company-profile.component.css'
})
export class CompanyProfileComponent implements OnInit{

constructor(private companyService: CompanyService){};

ngOnInit(): void {
this.getCompanyProfile();
}

public applications: Application[] = [];
public jobpostings: Jobposting[] = [];
public user: User = new User;

public selectedJobPosting: Jobposting = new Jobposting();

public selectedCompany: Company = new Company;

public showCompanyDetails(companyId: number){
  this.companyService.show(companyId).subscribe({
    next: (company) => {
      this.selectedCompany = company;
    },
    error: (err) => {
      console.error("CompanyComponent.ts: error loading company page");
      console.error(err);
    },
  });
}

public getCompanyProfile(){
  this.companyService.getCompanyProfile().subscribe({
    next: (company) => {
      this.selectedCompany = company;
    },
    error: (err) => {
      console.error("CompanyComponent.ts: error loading company page");
      console.error(err);
    },
  })
}

public editCompany(company: Company){
  this.companyService.update(company).subscribe({
    next: (company) => {
      this.selectedCompany = company;
    },
    error: (err) => {
      console.error("CompanyComponent.ts: error loading company page");
      console.error(err);
    },
  });
}

public getJobPostings(companyId: number){
  this.companyService.findJobPostings(companyId).subscribe({
    next: (jobPostings) => {
      this.jobpostings = jobPostings;
    },
    error: (err) => {
      console.error("CompanyComponent.ts: error loading company job postings");
      console.error(err);
    },
  });
}

public addNewJobPosting(companyId: number){
  this.companyService.addNewJobPosting(companyId).subscribe({
    next: (jobPosting) => {
      this.selectedJobPosting = jobPosting;
    },
    error: (err) => {
      console.error("CompanyComponent.ts: error creating new job posting");
      console.error(err);
    },
  })
}




}

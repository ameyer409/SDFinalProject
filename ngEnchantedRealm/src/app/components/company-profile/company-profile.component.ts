import { User } from './../../models/user';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Application } from '../../models/application';
import { Jobposting } from '../../models/jobposting';
import { Company } from '../../models/company';
import { CompanyService } from '../../services/company.service';
import { RouterLink } from '@angular/router';


@Component({
  selector: 'app-company-profile',
  standalone: true,
  imports: [FormsModule,
            CommonModule,
          RouterLink],
  templateUrl: './company-profile.component.html',
  styleUrl: './company-profile.component.css'
})
export class CompanyProfileComponent implements OnInit{

constructor(private companyService: CompanyService){};
public applications: Application[] = [];
public jobpostings: Jobposting[] = [];
public user: User = new User;

public companyToEdit: Company | null = null;

public selectedJobPosting: Jobposting | null = null;

public selectedCompany: Company = new Company;


ngOnInit(): void {
this.getCompanyProfile();
}



public showCompanyDetails(companyId: number){
  this.companyService.show(companyId).subscribe({
    next: (company) => {
      this.selectedCompany = company;
      console.log(companyId)
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
      this.getJobPostings(this.selectedCompany.id);
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
      this.companyToEdit = company;
    },
    error: (err) => {
      console.error("CompanyComponent.ts: error loading company page");
      console.error(err);
    },
  });
}

public getJobPostings(id: number){
  this.companyService.findJobPostings(id).subscribe({
    next: (jobPostings) => {
      this.jobpostings = jobPostings;
      console.log(jobPostings);
    },
    error: (err) => {
      console.error("CompanyComponent.ts: error loading company job postings");
      console.error(err);
    },
  });
}

public showAddJobPostingForm(){
  this.selectedJobPosting = new Jobposting();
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

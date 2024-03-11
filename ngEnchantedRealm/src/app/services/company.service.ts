import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { Company } from '../models/company';
import { Jobposting } from '../models/jobposting';
import { Application } from '../models/application';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  private url = environment.baseUrl + 'api/companies';

  constructor(
    private http: HttpClient,
    private auth: AuthService
    ) { }

    getHttpOptions() {
      let options = {
        headers: {
          Authorization: 'Basic ' + this.auth.getCredentials(),
            'X-Requested-With': 'XMLHttpRequest',
        },
      };
      return options;
    }

    public index(): Observable<Company[]> {
      return this.http.get<Company[]>(this.url, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          return throwError(
            () => new Error('CompanyService.index(): error retrieving companies: ' + err)
          );
        })
      );
    }

    public show(id: number): Observable<Company> {
      return this.http.get<Company>(this.url + '/' + id, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          return throwError(
            () => new Error('CompanyService.show: error finding Company: ' + err)
          );
        })
      );
    }

    public create(company: Company): Observable<Company> {
      return this.http.post<Company>(this.url, company, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          return throwError(
            () => new Error('companyService.create: error creating company: ' + err)
          );
        })
      );
    }

    public update(company: Company): Observable<Company> {

      return this.http.put<Company>(this.url + '/' + company.id, company, this.getHttpOptions()).pipe(
        catchError((err: any) => {
          return throwError(
            () => new Error('CompanyService.update: error updating Company: ' + err)
          );
        })
      );
    }

    public destroy(id: number): Observable<Company> {
      return this.http.put<Company>(this.url + '/' + id + "/disable", this.getHttpOptions()).pipe(
        catchError((err: any) => {
          return throwError(
            () => new Error('CompanyService.destroy: error deleting Company: ' + err)
          );
        })
      );
    }

    public findJobPostings(id: number): Observable<Jobposting[]> {
      return this.http.get<Jobposting[]>(this.url + '/' + id + '/jobpostings', this.getHttpOptions()).pipe(
        catchError((err: any) => {
          return throwError(
            () => new Error('CompanyService.findJobPostings(): error retrieving Job Postings: ' + err)
          );
        })
      );
    }

    public findJobPostingApplications(id: number, jid: number): Observable<Application[]> {
      return this.http.get<Application[]>(this.url + '/' + id + '/jobpostings/' + jid + '/applications', this.getHttpOptions()).pipe(
        catchError((err: any) => {
          return throwError(
            () => new Error('CompanyService.findJobPostingApplications(): error retrieving applications: ' + err)
          );
        })
      );
    }

    public addNewJobPosting(id: number): Observable<Jobposting>{
      return this.http.post<Jobposting>(this.url + '/' + id + '/jobpostings', this.getHttpOptions()).pipe(
        catchError((err: any) => {
          return throwError(
            () => new Error('CompanyService.addNewJobPosting(): error creating job posting: ' + err)
          );
        })
      );
    }

}

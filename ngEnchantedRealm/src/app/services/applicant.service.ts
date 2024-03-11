import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Applicant } from '../models/applicant';
import { Observable, catchError, throwError } from 'rxjs';
import { DatePipe } from '@angular/common';

@Injectable({
  providedIn: 'root'
})
export class ApplicantService {

  private url = environment.baseUrl + 'api/applicants';

  constructor(
    private http: HttpClient,
    private auth: AuthService,
    private datePipe: DatePipe
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

  public getApplicant(): Observable<Applicant> {
    return this.http.get<Applicant>(this.url + '/applicant', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () => new Error('applicantService.show: error finding Applicant: ' + err)
        );
      })
    );
  }

  public show(id: number): Observable<Applicant> {
    return this.http.get<Applicant>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () => new Error('applicantService.show: error finding Applicant: ' + err)
        );
      })
    );
  }

  public create(applicant: Applicant): Observable<Applicant> {
    return this.http.post<Applicant>(this.url, applicant, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () => new Error('applicantService.create: error creating Applicant: ' + err)
        );
      })
    );
  }

  public update(applicant: Applicant): Observable<Applicant> {
    if(applicant.birthdate !== '' && applicant.birthdate !== null) {
      applicant.birthdate = this.datePipe.transform(new Date(applicant.birthdate), 'fullDateTime');
    }
    else {
      applicant.birthdate = '';
    }
    return this.http.put<Applicant>(this.url + '/' + applicant.id, applicant, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () => new Error('applicantService.update: error updating applicant: ' + err)
        );
      })
    );
  }

  public destroy(id: number): Observable<Applicant> {
    return this.http.put<Applicant>(this.url + '/' + id + "/disable", this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () => new Error('applicantService.destroy: error deleting Applicant: ' + err)
        );
      })
    );
  }

}

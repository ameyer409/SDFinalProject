import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { Jobposting } from '../models/jobposting';

@Injectable({
  providedIn: 'root'
})
export class JobpostingService {

  private url = environment.baseUrl + 'api/jobpostings';

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

  public index(): Observable<Jobposting[]> {
    return this.http.get<Jobposting[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () => new Error('JobPostingService.index(): error retrieving jobpostings: ' + err)
        );
      })
    );
  }

  public show(id: number): Observable<Jobposting> {
    return this.http.get<Jobposting>(this.url + '/' + id, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        return throwError(
          () => new Error('JobPostingService.show: error finding JobPosting: ' + err)
        );
      })
    );
  }

}

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobDetailApplicantViewComponent } from './job-detail-applicant-view.component';

describe('JobDetailApplicantViewComponent', () => {
  let component: JobDetailApplicantViewComponent;
  let fixture: ComponentFixture<JobDetailApplicantViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JobDetailApplicantViewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(JobDetailApplicantViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobPostingCompanyViewComponent } from './job-posting-company-view.component';

describe('JobPostingCompanyViewComponent', () => {
  let component: JobPostingCompanyViewComponent;
  let fixture: ComponentFixture<JobPostingCompanyViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JobPostingCompanyViewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(JobPostingCompanyViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

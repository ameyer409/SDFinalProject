import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobDetailCompanyViewComponent } from './job-detail-company-view.component';

describe('JobDetailCompanyViewComponent', () => {
  let component: JobDetailCompanyViewComponent;
  let fixture: ComponentFixture<JobDetailCompanyViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JobDetailCompanyViewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(JobDetailCompanyViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobDetailGuestViewComponent } from './job-detail-guest-view.component';

describe('JobDetailGuestViewComponent', () => {
  let component: JobDetailGuestViewComponent;
  let fixture: ComponentFixture<JobDetailGuestViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [JobDetailGuestViewComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(JobDetailGuestViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

import { Applicant } from "./applicant";
import { Jobposting } from "./jobposting";
import { Status } from "./status";

export class Application {
  id: number;
  notes: string;
  jobPosting: Jobposting;
  applicant: Applicant;
  status: Status;

  constructor(
    id: number = 0,
    notes: string = '',
    jobPosting: Jobposting = new Jobposting(),
    applicant: Applicant = new Applicant(),
    status: Status = new Status()
  ){
    this.id = id;
    this.notes = notes;
    this.jobPosting = jobPosting;
    this.applicant = applicant;
    this.status = status;
  }
}

import { Address } from "./address";
import { Application } from "./application";
import { User } from "./user";

export class Applicant {
  id: number;
  firstName: string;
  lastName: string;
  dateOfBirth: string | null;
  email: string;
  eligibleToWork: boolean;
  relocation: boolean;
  profileImg: string;
  aboutMe: string;
  user: User;
  address: Address;
  applications: Application[];

  constructor(
    id: number = 0,
    firstName: string = '',
    lastName: string = '',
    birthdate: string = '',
    email: string = '',
    eligibleToWork: boolean = false,
    relocation: boolean = false,
    profileImg: string = '',
    aboutMe: string = '',
    user: User = new User(),
    address: Address = new Address(),
    applications: Application[] = []
  ){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = birthdate;
    this.email = email;
    this.eligibleToWork = eligibleToWork;
    this.relocation = relocation;
    this.profileImg = profileImg;
    this.aboutMe = aboutMe;
    this.user = user;
    this.address = address;
    this.applications = applications;
  }
}

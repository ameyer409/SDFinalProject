import { User } from "./user";

export class Applicant {
  id: number;
  firstName: string;
  lastName: string;
  birthdate: string | null;
  email: string;
  eligibleToWork: boolean;
  relocation: boolean;
  profileImg: string;
  aboutMe: string;
  user: {
    id: number,
    username: string,
    password: string,
    enabled: boolean,
    role: string
  };

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
    user: User = new User()
  ){
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.birthdate = birthdate;
    this.email = email;
    this.eligibleToWork = eligibleToWork;
    this.relocation = relocation;
    this.profileImg = profileImg;
    this.aboutMe = aboutMe;
    this.user = user;
  }
}

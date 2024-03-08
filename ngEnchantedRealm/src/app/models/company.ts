import { Address } from "./address";
import { User } from "./user";

export class Company {
  id: number;
  name: string;
  overview: string;
  enabled: boolean;
  logo: string;
  user: {
    id: number,
    username: string,
    password: string,
    enabled: boolean,
    role: string
  };
  address: {
    id: number,
    street: string,
    city: string,
    state: string,
    zipCode: string
  }

  constructor(
    id: number = 0,
    name: string = '',
    overview: string = '',
    enabled: boolean = true,
    logo: string = '',
    user: User = new User(),
    address: Address = new Address()
  ){
    this.id = id;
    this.name = name;
    this.overview = overview;
    this.enabled = enabled;
    this.logo = logo;
    this.user = user;
    this.address = address;
  }
}

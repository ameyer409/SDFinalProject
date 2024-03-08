export class Jobposting {
  id: number;
  title: string;
  description: string;
  minimumSalary: number;
  maximumSalary: number;
  positions: number;
  enabled: boolean;
  requirements: string;

  constructor(
    id: number = 0,
    title: string = '',
    description: string = '',
    minimumSalary: number = 0,
    maximumSalary: number = 0,
    positions: number = 0,
    enabled: boolean = false,
    requirements: string = ''
  ){
    this.id = id;
    this.title = title;
    this.description = description;
    this.minimumSalary = minimumSalary;
    this.maximumSalary = maximumSalary;
    this.positions = positions;
    this.enabled = enabled;
    this.requirements = requirements;
  }
}

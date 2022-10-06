export interface IDepartment {
  id?: number;
  depID?: string | null;
  depName?: string | null;
  available?: number | null;
  relesed?: number | null;
  assigned?: number | null;
  hcode?: string | null;
}

export class Department implements IDepartment {
  constructor(
    public id?: number,
    public depID?: string | null,
    public depName?: string | null,
    public available?: number | null,
    public relesed?: number | null,
    public assigned?: number | null,
    public hcode?: string | null
  ) {}
}

export function getDepartmentIdentifier(department: IDepartment): number | undefined {
  return department.id;
}

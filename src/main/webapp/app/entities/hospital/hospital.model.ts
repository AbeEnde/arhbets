export interface IHospital {
  id?: number;
  no?: number | null;
  hospitalName?: string | null;
  code?: string | null;
}

export class Hospital implements IHospital {
  constructor(public id?: number, public no?: number | null, public hospitalName?: string | null, public code?: string | null) {}
}

export function getHospitalIdentifier(hospital: IHospital): number | undefined {
  return hospital.id;
}

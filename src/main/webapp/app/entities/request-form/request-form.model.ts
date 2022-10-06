import dayjs from 'dayjs/esm';

export interface IRequestForm {
  id?: number;
  name?: string;
  sex?: string | null;
  profession?: string | null;
  c_wzone?: string | null;
  c_wworeda?: string | null;
  c_whealthfacility?: string | null;
  firstchoice?: string | null;
  secondchoice?: string | null;
  thirdchoice?: string | null;
  selfexpirence?: dayjs.Dayjs | null;
  saposeexperience?: string | null;
  no?: string | null;
}

export class RequestForm implements IRequestForm {
  constructor(
    public id?: number,
    public name?: string,
    public sex?: string | null,
    public profession?: string | null,
    public c_wzone?: string | null,
    public c_wworeda?: string | null,
    public c_whealthfacility?: string | null,
    public firstchoice?: string | null,
    public secondchoice?: string | null,
    public thirdchoice?: string | null,
    public selfexpirence?: dayjs.Dayjs | null,
    public saposeexperience?: string | null,
    public no?: string | null
  ) {}
}

export function getRequestFormIdentifier(requestForm: IRequestForm): number | undefined {
  return requestForm.id;
}

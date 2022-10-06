export interface IRequest {
    id?: number;
    name?: string | null;
    sex?: string | null;
    profession?: string | null;
    cwzone?: string | null;
    cwworeda?: string | null;
    cwfacility?: string | null;
    firstchoice?: string | null;
    code1?: string | null;
    secondchoice?: string | null;
    code2?: string | null;
    thirdchoice?: string | null;
    code3?: string | null;
    expryear?: string | null;
    exprmonth?: string | null;
    exprday?: string | null;
    spexpryear?: string | null;
    spexprmonth?: string | null;
    spexprday?: string | null;
    fileContentType?: string | null;
    file?: string | null;
  }
  
  export class Request implements IRequest {
    constructor(
      public id?: number,
      public name?: string | null,
      public sex?: string | null,
      public profession?: string | null,
      public cwzone?: string | null,
      public cwworeda?: string | null,
      public cwfacility?: string | null,
      public firstchoice?: string | null,
      public code1?: string | null,
      public secondchoice?: string | null,
      public code2?: string | null,
      public thirdchoice?: string | null,
      public code3?: string | null,
      public expryear?: string | null,
      public exprmonth?: string | null,
      public exprday?: string | null,
      public spexpryear?: string | null,
      public spexprmonth?: string | null,
      public spexprday?: string | null,
      public fileContentType?: string | null,
      public file?: string | null
    ) {}
  }
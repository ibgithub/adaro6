import { Moment } from 'moment';

export interface IStepsMySuffix {
    id?: number;
    dateTime?: Moment;
    stepsCount?: number;
    stepsEmployeeId?: number;
}

export class StepsMySuffix implements IStepsMySuffix {
    constructor(public id?: number, public dateTime?: Moment, public stepsCount?: number, public stepsEmployeeId?: number) {}
}

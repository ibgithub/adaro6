import { Moment } from 'moment';

export interface IHeartRateMySuffix {
    id?: number;
    dateTime?: Moment;
    heartRate?: number;
    hREmployeeId?: number;
}

export class HeartRateMySuffix implements IHeartRateMySuffix {
    constructor(public id?: number, public dateTime?: Moment, public heartRate?: number, public hREmployeeId?: number) {}
}

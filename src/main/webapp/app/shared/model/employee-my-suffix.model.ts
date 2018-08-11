import { Moment } from 'moment';
import { IHeartRateMySuffix } from 'app/shared/model/heart-rate-my-suffix.model';
import { IStepsMySuffix } from 'app/shared/model/steps-my-suffix.model';

export interface IEmployeeMySuffix {
    id?: number;
    code?: string;
    firstName?: string;
    lastName?: string;
    phoneNumber?: string;
    email?: string;
    birthDate?: Moment;
    age?: number;
    sex?: string;
    height?: number;
    weight?: number;
    heartRate?: number;
    stepsDay?: number;
    idealHeartRate?: number;
    idealStepsDay?: number;
    employeeHRS?: IHeartRateMySuffix[];
    employeeSteps?: IStepsMySuffix[];
}

export class EmployeeMySuffix implements IEmployeeMySuffix {
    constructor(
        public id?: number,
        public code?: string,
        public firstName?: string,
        public lastName?: string,
        public phoneNumber?: string,
        public email?: string,
        public birthDate?: Moment,
        public age?: number,
        public sex?: string,
        public height?: number,
        public weight?: number,
        public heartRate?: number,
        public stepsDay?: number,
        public idealHeartRate?: number,
        public idealStepsDay?: number,
        public employeeHRS?: IHeartRateMySuffix[],
        public employeeSteps?: IStepsMySuffix[]
    ) {}
}

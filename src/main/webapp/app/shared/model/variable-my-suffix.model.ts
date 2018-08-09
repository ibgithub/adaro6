export interface IVariableMySuffix {
    id?: number;
    name?: string;
    value?: string;
    desc?: string;
}

export class VariableMySuffix implements IVariableMySuffix {
    constructor(public id?: number, public name?: string, public value?: string, public desc?: string) {}
}

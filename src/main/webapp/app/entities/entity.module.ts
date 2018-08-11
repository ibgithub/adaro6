import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { Adaro6EmployeeMySuffixModule } from 'app/entities/employee-my-suffix/employee-my-suffix.module';
import { Adaro6VariableMySuffixModule } from 'app/entities/variable-my-suffix/variable-my-suffix.module';
import { Adaro6HeartRateMySuffixModule } from 'app/entities/heart-rate-my-suffix/heart-rate-my-suffix.module';
import { Adaro6StepsMySuffixModule } from 'app/entities/steps-my-suffix/steps-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        Adaro6EmployeeMySuffixModule,
        Adaro6VariableMySuffixModule,
        Adaro6HeartRateMySuffixModule,
        Adaro6StepsMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Adaro6EntityModule {}

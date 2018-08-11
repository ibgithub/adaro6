import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Adaro6SharedModule } from 'app/shared';
import {
    StepsMySuffixComponent,
    StepsMySuffixDetailComponent,
    StepsMySuffixUpdateComponent,
    StepsMySuffixDeletePopupComponent,
    StepsMySuffixDeleteDialogComponent,
    stepsRoute,
    stepsPopupRoute
} from 'app/entities/steps-my-suffix';

const ENTITY_STATES = [...stepsRoute, ...stepsPopupRoute];

@NgModule({
    imports: [Adaro6SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        StepsMySuffixComponent,
        StepsMySuffixDetailComponent,
        StepsMySuffixUpdateComponent,
        StepsMySuffixDeleteDialogComponent,
        StepsMySuffixDeletePopupComponent
    ],
    entryComponents: [
        StepsMySuffixComponent,
        StepsMySuffixUpdateComponent,
        StepsMySuffixDeleteDialogComponent,
        StepsMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Adaro6StepsMySuffixModule {}

import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Adaro6SharedModule } from 'app/shared';
import {
    VariableMySuffixComponent,
    VariableMySuffixDetailComponent,
    VariableMySuffixUpdateComponent,
    VariableMySuffixDeletePopupComponent,
    VariableMySuffixDeleteDialogComponent,
    variableRoute,
    variablePopupRoute
} from 'app/entities/variable-my-suffix';

const ENTITY_STATES = [...variableRoute, ...variablePopupRoute];

@NgModule({
    imports: [Adaro6SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        VariableMySuffixComponent,
        VariableMySuffixDetailComponent,
        VariableMySuffixUpdateComponent,
        VariableMySuffixDeleteDialogComponent,
        VariableMySuffixDeletePopupComponent
    ],
    entryComponents: [
        VariableMySuffixComponent,
        VariableMySuffixUpdateComponent,
        VariableMySuffixDeleteDialogComponent,
        VariableMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Adaro6VariableMySuffixModule {}

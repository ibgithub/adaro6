import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { Adaro6SharedModule } from 'app/shared';
import {
    HeartRateMySuffixComponent,
    HeartRateMySuffixDetailComponent,
    HeartRateMySuffixUpdateComponent,
    HeartRateMySuffixDeletePopupComponent,
    HeartRateMySuffixDeleteDialogComponent,
    heartRateRoute,
    heartRatePopupRoute
} from 'app/entities/heart-rate-my-suffix';

const ENTITY_STATES = [...heartRateRoute, ...heartRatePopupRoute];

@NgModule({
    imports: [Adaro6SharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        HeartRateMySuffixComponent,
        HeartRateMySuffixDetailComponent,
        HeartRateMySuffixUpdateComponent,
        HeartRateMySuffixDeleteDialogComponent,
        HeartRateMySuffixDeletePopupComponent
    ],
    entryComponents: [
        HeartRateMySuffixComponent,
        HeartRateMySuffixUpdateComponent,
        HeartRateMySuffixDeleteDialogComponent,
        HeartRateMySuffixDeletePopupComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Adaro6HeartRateMySuffixModule {}

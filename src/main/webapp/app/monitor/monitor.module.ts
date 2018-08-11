import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { Adaro6HeartRateModule } from './heartrate/heartrate.module';
import { Adaro6StepsModule } from './steps/steps.module';

@NgModule({
    imports: [
        Adaro6HeartRateModule,
        Adaro6StepsModule,
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Adaro6MonitorModule {}

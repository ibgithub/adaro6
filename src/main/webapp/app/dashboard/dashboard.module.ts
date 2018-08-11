import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { Adaro6BarchartModule } from 'app/dashboard/barchart/barchart.module';
import { Adaro6DoughnutchartModule } from 'app/dashboard/doughnutchart/doughnutchart.module';
import { Adaro6LinechartModule } from 'app/dashboard/linechart/linechart.module';
import { Adaro6PiechartModule } from 'app/dashboard/piechart/piechart.module';
import { Adaro6PolarareachartModule } from 'app/dashboard/polarareachart/polarareachart.module';
import { Adaro6RadarchartModule } from 'app/dashboard/radarchart/radarchart.module';

@NgModule({
    imports: [
        Adaro6BarchartModule,
        Adaro6DoughnutchartModule,
        Adaro6LinechartModule,
        Adaro6PiechartModule,
        Adaro6PolarareachartModule,
        Adaro6RadarchartModule,
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class Adaro6DashboardModule {}

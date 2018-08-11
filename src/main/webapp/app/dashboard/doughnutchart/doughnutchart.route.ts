import { Route } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { DoughnutchartComponent } from 'app/dashboard/doughnutchart/doughnutchart.component';

export const doughnutchartRoute: Route = {
    path: 'doughnutchart',
    component: DoughnutchartComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'dashboard.doughnutchart.home.title'
    },
    canActivate: [UserRouteAccessService]
};

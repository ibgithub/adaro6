import { Route } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { PiechartComponent } from 'app/dashboard/piechart/piechart.component';

export const piechartRoute: Route = {
    path: 'piechart',
    component: PiechartComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'dashboard.piechart.home.title'
    },
    canActivate: [UserRouteAccessService]
};

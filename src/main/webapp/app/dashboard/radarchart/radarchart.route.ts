import { Route } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { RadarchartComponent } from 'app/dashboard/radarchart/radarchart.component';

export const radarchartRoute: Route = {
    path: 'radarchart',
    component: RadarchartComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'dashboard.radarchart.home.title'
    },
    canActivate: [UserRouteAccessService]
};

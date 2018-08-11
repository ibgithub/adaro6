import { Route } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { HeartRateComponent } from 'app/monitor/heartrate/heartrate.component';

export const heartRateRoute: Route = {
    path: 'heartrate',
    component: HeartRateComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'monitor.heartrate.home.title'
    },
    canActivate: [UserRouteAccessService]
};

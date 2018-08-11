import { Route } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { StepsComponent } from 'app/monitor/steps/steps.component';

export const stepsRoute: Route = {
    path: 'steps',
    component: StepsComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'monitor.steps.home.title'
    },
    canActivate: [UserRouteAccessService]
};

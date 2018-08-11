import { Route } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { LinechartComponent } from 'app/dashboard/linechart/linechart.component';

export const linechartRoute: Route = {
    path: 'linechart',
    component: LinechartComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'dashboard.linechart.home.title'
    },
    canActivate: [UserRouteAccessService]
};

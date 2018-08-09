import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { StepsMySuffix } from 'app/shared/model/steps-my-suffix.model';
import { StepsMySuffixService } from './steps-my-suffix.service';
import { StepsMySuffixComponent } from './steps-my-suffix.component';
import { StepsMySuffixDetailComponent } from './steps-my-suffix-detail.component';
import { StepsMySuffixUpdateComponent } from './steps-my-suffix-update.component';
import { StepsMySuffixDeletePopupComponent } from './steps-my-suffix-delete-dialog.component';
import { IStepsMySuffix } from 'app/shared/model/steps-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class StepsMySuffixResolve implements Resolve<IStepsMySuffix> {
    constructor(private service: StepsMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((steps: HttpResponse<StepsMySuffix>) => steps.body));
        }
        return of(new StepsMySuffix());
    }
}

export const stepsRoute: Routes = [
    {
        path: 'steps-my-suffix',
        component: StepsMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'adaro6App.steps.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'steps-my-suffix/:id/view',
        component: StepsMySuffixDetailComponent,
        resolve: {
            steps: StepsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.steps.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'steps-my-suffix/new',
        component: StepsMySuffixUpdateComponent,
        resolve: {
            steps: StepsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.steps.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'steps-my-suffix/:id/edit',
        component: StepsMySuffixUpdateComponent,
        resolve: {
            steps: StepsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.steps.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const stepsPopupRoute: Routes = [
    {
        path: 'steps-my-suffix/:id/delete',
        component: StepsMySuffixDeletePopupComponent,
        resolve: {
            steps: StepsMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.steps.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

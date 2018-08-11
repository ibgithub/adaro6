import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { HeartRateMySuffix } from 'app/shared/model/heart-rate-my-suffix.model';
import { HeartRateMySuffixService } from 'app/entities/heart-rate-my-suffix/heart-rate-my-suffix.service';
import { HeartRateMySuffixComponent } from 'app/entities/heart-rate-my-suffix/heart-rate-my-suffix.component';
import { HeartRateMySuffixDetailComponent } from 'app/entities/heart-rate-my-suffix/heart-rate-my-suffix-detail.component';
import { HeartRateMySuffixUpdateComponent } from 'app/entities/heart-rate-my-suffix/heart-rate-my-suffix-update.component';
import { HeartRateMySuffixDeletePopupComponent } from 'app/entities/heart-rate-my-suffix/heart-rate-my-suffix-delete-dialog.component';
import { IHeartRateMySuffix } from 'app/shared/model/heart-rate-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class HeartRateMySuffixResolve implements Resolve<IHeartRateMySuffix> {
    constructor(private service: HeartRateMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((heartRate: HttpResponse<HeartRateMySuffix>) => heartRate.body));
        }
        return of(new HeartRateMySuffix());
    }
}

export const heartRateRoute: Routes = [
    {
        path: 'heart-rate-my-suffix',
        component: HeartRateMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'adaro6App.heartRate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'heart-rate-my-suffix/:id/view',
        component: HeartRateMySuffixDetailComponent,
        resolve: {
            heartRate: HeartRateMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.heartRate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'heart-rate-my-suffix/new',
        component: HeartRateMySuffixUpdateComponent,
        resolve: {
            heartRate: HeartRateMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.heartRate.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'heart-rate-my-suffix/:id/edit',
        component: HeartRateMySuffixUpdateComponent,
        resolve: {
            heartRate: HeartRateMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.heartRate.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const heartRatePopupRoute: Routes = [
    {
        path: 'heart-rate-my-suffix/:id/delete',
        component: HeartRateMySuffixDeletePopupComponent,
        resolve: {
            heartRate: HeartRateMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.heartRate.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

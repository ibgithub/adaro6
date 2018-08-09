import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { VariableMySuffix } from 'app/shared/model/variable-my-suffix.model';
import { VariableMySuffixService } from './variable-my-suffix.service';
import { VariableMySuffixComponent } from './variable-my-suffix.component';
import { VariableMySuffixDetailComponent } from './variable-my-suffix-detail.component';
import { VariableMySuffixUpdateComponent } from './variable-my-suffix-update.component';
import { VariableMySuffixDeletePopupComponent } from './variable-my-suffix-delete-dialog.component';
import { IVariableMySuffix } from 'app/shared/model/variable-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class VariableMySuffixResolve implements Resolve<IVariableMySuffix> {
    constructor(private service: VariableMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((variable: HttpResponse<VariableMySuffix>) => variable.body));
        }
        return of(new VariableMySuffix());
    }
}

export const variableRoute: Routes = [
    {
        path: 'variable-my-suffix',
        component: VariableMySuffixComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.variable.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'variable-my-suffix/:id/view',
        component: VariableMySuffixDetailComponent,
        resolve: {
            variable: VariableMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.variable.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'variable-my-suffix/new',
        component: VariableMySuffixUpdateComponent,
        resolve: {
            variable: VariableMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.variable.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'variable-my-suffix/:id/edit',
        component: VariableMySuffixUpdateComponent,
        resolve: {
            variable: VariableMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.variable.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const variablePopupRoute: Routes = [
    {
        path: 'variable-my-suffix/:id/delete',
        component: VariableMySuffixDeletePopupComponent,
        resolve: {
            variable: VariableMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.variable.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

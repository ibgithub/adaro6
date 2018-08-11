import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { of } from 'rxjs';
import { map } from 'rxjs/operators';
import { EmployeeMySuffix } from 'app/shared/model/employee-my-suffix.model';
import { EmployeeMySuffixService } from 'app/entities/employee-my-suffix/employee-my-suffix.service';
import { EmployeeMySuffixComponent } from 'app/entities/employee-my-suffix/employee-my-suffix.component';
import { EmployeeMySuffixDetailComponent } from 'app/entities/employee-my-suffix/employee-my-suffix-detail.component';
import { EmployeeMySuffixUpdateComponent } from 'app/entities/employee-my-suffix/employee-my-suffix-update.component';
import { EmployeeMySuffixDeletePopupComponent } from 'app/entities/employee-my-suffix/employee-my-suffix-delete-dialog.component';
import { IEmployeeMySuffix } from 'app/shared/model/employee-my-suffix.model';

@Injectable({ providedIn: 'root' })
export class EmployeeMySuffixResolve implements Resolve<IEmployeeMySuffix> {
    constructor(private service: EmployeeMySuffixService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(map((employee: HttpResponse<EmployeeMySuffix>) => employee.body));
        }
        return of(new EmployeeMySuffix());
    }
}

export const employeeRoute: Routes = [
    {
        path: 'employee-my-suffix',
        component: EmployeeMySuffixComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'adaro6App.employee.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employee-my-suffix/:id/view',
        component: EmployeeMySuffixDetailComponent,
        resolve: {
            employee: EmployeeMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.employee.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employee-my-suffix/new',
        component: EmployeeMySuffixUpdateComponent,
        resolve: {
            employee: EmployeeMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.employee.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'employee-my-suffix/:id/edit',
        component: EmployeeMySuffixUpdateComponent,
        resolve: {
            employee: EmployeeMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.employee.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const employeePopupRoute: Routes = [
    {
        path: 'employee-my-suffix/:id/delete',
        component: EmployeeMySuffixDeletePopupComponent,
        resolve: {
            employee: EmployeeMySuffixResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'adaro6App.employee.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];

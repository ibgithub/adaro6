import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IHeartRateMySuffix } from 'app/shared/model/heart-rate-my-suffix.model';
import { HeartRateMySuffixService } from 'app/entities/heart-rate-my-suffix/heart-rate-my-suffix.service';
import { IEmployeeMySuffix } from 'app/shared/model/employee-my-suffix.model';
import { EmployeeMySuffixService } from 'app/entities/employee-my-suffix';

@Component({
    selector: 'jhi-heart-rate-my-suffix-update',
    templateUrl: './heart-rate-my-suffix-update.component.html'
})
export class HeartRateMySuffixUpdateComponent implements OnInit {
    private _heartRate: IHeartRateMySuffix;
    isSaving: boolean;

    employees: IEmployeeMySuffix[];
    dateTime: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private heartRateService: HeartRateMySuffixService,
        private employeeService: EmployeeMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ heartRate }) => {
            this.heartRate = heartRate;
        });
        this.employeeService.query().subscribe(
            (res: HttpResponse<IEmployeeMySuffix[]>) => {
                this.employees = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.heartRate.dateTime = moment(this.dateTime, DATE_TIME_FORMAT);
        if (this.heartRate.id !== undefined) {
            this.subscribeToSaveResponse(this.heartRateService.update(this.heartRate));
        } else {
            this.subscribeToSaveResponse(this.heartRateService.create(this.heartRate));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IHeartRateMySuffix>>) {
        result.subscribe((res: HttpResponse<IHeartRateMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackEmployeeById(index: number, item: IEmployeeMySuffix) {
        return item.id;
    }
    get heartRate() {
        return this._heartRate;
    }

    set heartRate(heartRate: IHeartRateMySuffix) {
        this._heartRate = heartRate;
        this.dateTime = moment(heartRate.dateTime).format(DATE_TIME_FORMAT);
    }
}

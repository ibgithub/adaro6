import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';

import { IStepsMySuffix } from 'app/shared/model/steps-my-suffix.model';
import { StepsMySuffixService } from 'app/entities/steps-my-suffix/steps-my-suffix.service';
import { IEmployeeMySuffix } from 'app/shared/model/employee-my-suffix.model';
import { EmployeeMySuffixService } from 'app/entities/employee-my-suffix';

@Component({
    selector: 'jhi-steps-my-suffix-update',
    templateUrl: './steps-my-suffix-update.component.html'
})
export class StepsMySuffixUpdateComponent implements OnInit {
    private _steps: IStepsMySuffix;
    isSaving: boolean;

    employees: IEmployeeMySuffix[];
    dateTime: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private stepsService: StepsMySuffixService,
        private employeeService: EmployeeMySuffixService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ steps }) => {
            this.steps = steps;
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
        this.steps.dateTime = moment(this.dateTime, DATE_TIME_FORMAT);
        if (this.steps.id !== undefined) {
            this.subscribeToSaveResponse(this.stepsService.update(this.steps));
        } else {
            this.subscribeToSaveResponse(this.stepsService.create(this.steps));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IStepsMySuffix>>) {
        result.subscribe((res: HttpResponse<IStepsMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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
    get steps() {
        return this._steps;
    }

    set steps(steps: IStepsMySuffix) {
        this._steps = steps;
        this.dateTime = moment(steps.dateTime).format(DATE_TIME_FORMAT);
    }
}

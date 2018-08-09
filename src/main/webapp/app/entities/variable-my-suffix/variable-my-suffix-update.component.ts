import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IVariableMySuffix } from 'app/shared/model/variable-my-suffix.model';
import { VariableMySuffixService } from './variable-my-suffix.service';

@Component({
    selector: 'jhi-variable-my-suffix-update',
    templateUrl: './variable-my-suffix-update.component.html'
})
export class VariableMySuffixUpdateComponent implements OnInit {
    private _variable: IVariableMySuffix;
    isSaving: boolean;

    constructor(private variableService: VariableMySuffixService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ variable }) => {
            this.variable = variable;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.variable.id !== undefined) {
            this.subscribeToSaveResponse(this.variableService.update(this.variable));
        } else {
            this.subscribeToSaveResponse(this.variableService.create(this.variable));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IVariableMySuffix>>) {
        result.subscribe((res: HttpResponse<IVariableMySuffix>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get variable() {
        return this._variable;
    }

    set variable(variable: IVariableMySuffix) {
        this._variable = variable;
    }
}

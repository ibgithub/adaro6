import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IVariableMySuffix } from 'app/shared/model/variable-my-suffix.model';
import { Principal } from 'app/core';
import { VariableMySuffixService } from './variable-my-suffix.service';

@Component({
    selector: 'jhi-variable-my-suffix',
    templateUrl: './variable-my-suffix.component.html'
})
export class VariableMySuffixComponent implements OnInit, OnDestroy {
    variables: IVariableMySuffix[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private variableService: VariableMySuffixService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.variableService.query().subscribe(
            (res: HttpResponse<IVariableMySuffix[]>) => {
                this.variables = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInVariables();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IVariableMySuffix) {
        return item.id;
    }

    registerChangeInVariables() {
        this.eventSubscriber = this.eventManager.subscribe('variableListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}

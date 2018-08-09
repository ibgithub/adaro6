import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVariableMySuffix } from 'app/shared/model/variable-my-suffix.model';

@Component({
    selector: 'jhi-variable-my-suffix-detail',
    templateUrl: './variable-my-suffix-detail.component.html'
})
export class VariableMySuffixDetailComponent implements OnInit {
    variable: IVariableMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ variable }) => {
            this.variable = variable;
        });
    }

    previousState() {
        window.history.back();
    }
}

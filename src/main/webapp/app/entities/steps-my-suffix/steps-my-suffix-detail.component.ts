import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IStepsMySuffix } from 'app/shared/model/steps-my-suffix.model';

@Component({
    selector: 'jhi-steps-my-suffix-detail',
    templateUrl: './steps-my-suffix-detail.component.html'
})
export class StepsMySuffixDetailComponent implements OnInit {
    steps: IStepsMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ steps }) => {
            this.steps = steps;
        });
    }

    previousState() {
        window.history.back();
    }
}

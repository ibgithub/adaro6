import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHeartRateMySuffix } from 'app/shared/model/heart-rate-my-suffix.model';

@Component({
    selector: 'jhi-heart-rate-my-suffix-detail',
    templateUrl: './heart-rate-my-suffix-detail.component.html'
})
export class HeartRateMySuffixDetailComponent implements OnInit {
    heartRate: IHeartRateMySuffix;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ heartRate }) => {
            this.heartRate = heartRate;
        });
    }

    previousState() {
        window.history.back();
    }
}

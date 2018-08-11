import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHeartRateMySuffix } from 'app/shared/model/heart-rate-my-suffix.model';
import { HeartRateMySuffixService } from 'app/entities/heart-rate-my-suffix/heart-rate-my-suffix.service';

@Component({
    selector: 'jhi-heart-rate-my-suffix-delete-dialog',
    templateUrl: './heart-rate-my-suffix-delete-dialog.component.html'
})
export class HeartRateMySuffixDeleteDialogComponent {
    heartRate: IHeartRateMySuffix;

    constructor(
        private heartRateService: HeartRateMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.heartRateService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'heartRateListModification',
                content: 'Deleted an heartRate'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-heart-rate-my-suffix-delete-popup',
    template: ''
})
export class HeartRateMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ heartRate }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(HeartRateMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.heartRate = heartRate;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}

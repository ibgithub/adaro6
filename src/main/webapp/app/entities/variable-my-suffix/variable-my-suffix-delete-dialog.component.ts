import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVariableMySuffix } from 'app/shared/model/variable-my-suffix.model';
import { VariableMySuffixService } from 'app/entities/variable-my-suffix/variable-my-suffix.service';

@Component({
    selector: 'jhi-variable-my-suffix-delete-dialog',
    templateUrl: './variable-my-suffix-delete-dialog.component.html'
})
export class VariableMySuffixDeleteDialogComponent {
    variable: IVariableMySuffix;

    constructor(
        private variableService: VariableMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.variableService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'variableListModification',
                content: 'Deleted an variable'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-variable-my-suffix-delete-popup',
    template: ''
})
export class VariableMySuffixDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ variable }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(VariableMySuffixDeleteDialogComponent as Component, {
                    size: 'lg',
                    backdrop: 'static'
                });
                this.ngbModalRef.componentInstance.variable = variable;
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

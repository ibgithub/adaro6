/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { Adaro6TestModule } from '../../../test.module';
import { StepsMySuffixDeleteDialogComponent } from 'app/entities/steps-my-suffix/steps-my-suffix-delete-dialog.component';
import { StepsMySuffixService } from 'app/entities/steps-my-suffix/steps-my-suffix.service';

describe('Component Tests', () => {
    describe('StepsMySuffix Management Delete Component', () => {
        let comp: StepsMySuffixDeleteDialogComponent;
        let fixture: ComponentFixture<StepsMySuffixDeleteDialogComponent>;
        let service: StepsMySuffixService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Adaro6TestModule],
                declarations: [StepsMySuffixDeleteDialogComponent]
            })
                .overrideTemplate(StepsMySuffixDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StepsMySuffixDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StepsMySuffixService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete(123);
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith(123);
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});

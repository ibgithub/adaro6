/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Adaro6TestModule } from '../../../test.module';
import { StepsMySuffixUpdateComponent } from 'app/entities/steps-my-suffix/steps-my-suffix-update.component';
import { StepsMySuffixService } from 'app/entities/steps-my-suffix/steps-my-suffix.service';
import { StepsMySuffix } from 'app/shared/model/steps-my-suffix.model';

describe('Component Tests', () => {
    describe('StepsMySuffix Management Update Component', () => {
        let comp: StepsMySuffixUpdateComponent;
        let fixture: ComponentFixture<StepsMySuffixUpdateComponent>;
        let service: StepsMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Adaro6TestModule],
                declarations: [StepsMySuffixUpdateComponent]
            })
                .overrideTemplate(StepsMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(StepsMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(StepsMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new StepsMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.steps = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new StepsMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.steps = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});

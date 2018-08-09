/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { Adaro6TestModule } from '../../../test.module';
import { VariableMySuffixUpdateComponent } from 'app/entities/variable-my-suffix/variable-my-suffix-update.component';
import { VariableMySuffixService } from 'app/entities/variable-my-suffix/variable-my-suffix.service';
import { VariableMySuffix } from 'app/shared/model/variable-my-suffix.model';

describe('Component Tests', () => {
    describe('VariableMySuffix Management Update Component', () => {
        let comp: VariableMySuffixUpdateComponent;
        let fixture: ComponentFixture<VariableMySuffixUpdateComponent>;
        let service: VariableMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Adaro6TestModule],
                declarations: [VariableMySuffixUpdateComponent]
            })
                .overrideTemplate(VariableMySuffixUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VariableMySuffixUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VariableMySuffixService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new VariableMySuffix(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.variable = entity;
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
                    const entity = new VariableMySuffix();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.variable = entity;
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

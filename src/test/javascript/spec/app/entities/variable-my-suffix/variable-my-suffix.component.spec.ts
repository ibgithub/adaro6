/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { Adaro6TestModule } from '../../../test.module';
import { VariableMySuffixComponent } from 'app/entities/variable-my-suffix/variable-my-suffix.component';
import { VariableMySuffixService } from 'app/entities/variable-my-suffix/variable-my-suffix.service';
import { VariableMySuffix } from 'app/shared/model/variable-my-suffix.model';

describe('Component Tests', () => {
    describe('VariableMySuffix Management Component', () => {
        let comp: VariableMySuffixComponent;
        let fixture: ComponentFixture<VariableMySuffixComponent>;
        let service: VariableMySuffixService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Adaro6TestModule],
                declarations: [VariableMySuffixComponent],
                providers: []
            })
                .overrideTemplate(VariableMySuffixComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(VariableMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(VariableMySuffixService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new VariableMySuffix(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.variables[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});

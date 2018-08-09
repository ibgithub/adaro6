/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Adaro6TestModule } from '../../../test.module';
import { VariableMySuffixDetailComponent } from 'app/entities/variable-my-suffix/variable-my-suffix-detail.component';
import { VariableMySuffix } from 'app/shared/model/variable-my-suffix.model';

describe('Component Tests', () => {
    describe('VariableMySuffix Management Detail Component', () => {
        let comp: VariableMySuffixDetailComponent;
        let fixture: ComponentFixture<VariableMySuffixDetailComponent>;
        const route = ({ data: of({ variable: new VariableMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Adaro6TestModule],
                declarations: [VariableMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(VariableMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(VariableMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.variable).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

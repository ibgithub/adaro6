/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Adaro6TestModule } from '../../../test.module';
import { StepsMySuffixDetailComponent } from 'app/entities/steps-my-suffix/steps-my-suffix-detail.component';
import { StepsMySuffix } from 'app/shared/model/steps-my-suffix.model';

describe('Component Tests', () => {
    describe('StepsMySuffix Management Detail Component', () => {
        let comp: StepsMySuffixDetailComponent;
        let fixture: ComponentFixture<StepsMySuffixDetailComponent>;
        const route = ({ data: of({ steps: new StepsMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Adaro6TestModule],
                declarations: [StepsMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(StepsMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StepsMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.steps).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

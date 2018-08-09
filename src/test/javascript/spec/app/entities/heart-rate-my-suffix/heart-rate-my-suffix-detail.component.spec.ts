/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { Adaro6TestModule } from '../../../test.module';
import { HeartRateMySuffixDetailComponent } from 'app/entities/heart-rate-my-suffix/heart-rate-my-suffix-detail.component';
import { HeartRateMySuffix } from 'app/shared/model/heart-rate-my-suffix.model';

describe('Component Tests', () => {
    describe('HeartRateMySuffix Management Detail Component', () => {
        let comp: HeartRateMySuffixDetailComponent;
        let fixture: ComponentFixture<HeartRateMySuffixDetailComponent>;
        const route = ({ data: of({ heartRate: new HeartRateMySuffix(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [Adaro6TestModule],
                declarations: [HeartRateMySuffixDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(HeartRateMySuffixDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(HeartRateMySuffixDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.heartRate).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});

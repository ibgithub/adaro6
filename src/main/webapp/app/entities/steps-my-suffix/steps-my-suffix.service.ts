import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IStepsMySuffix } from 'app/shared/model/steps-my-suffix.model';

type EntityResponseType = HttpResponse<IStepsMySuffix>;
type EntityArrayResponseType = HttpResponse<IStepsMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class StepsMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/steps';

    constructor(private http: HttpClient) {}

    create(steps: IStepsMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(steps);
        return this.http
            .post<IStepsMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(steps: IStepsMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(steps);
        return this.http
            .put<IStepsMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IStepsMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IStepsMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(steps: IStepsMySuffix): IStepsMySuffix {
        const copy: IStepsMySuffix = Object.assign({}, steps, {
            dateTime: steps.dateTime != null && steps.dateTime.isValid() ? steps.dateTime.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.dateTime = res.body.dateTime != null ? moment(res.body.dateTime) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((steps: IStepsMySuffix) => {
            steps.dateTime = steps.dateTime != null ? moment(steps.dateTime) : null;
        });
        return res;
    }
}

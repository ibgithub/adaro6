import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IHeartRateMySuffix } from 'app/shared/model/heart-rate-my-suffix.model';

type EntityResponseType = HttpResponse<IHeartRateMySuffix>;
type EntityArrayResponseType = HttpResponse<IHeartRateMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class HeartRateMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/heart-rates';

    constructor(private http: HttpClient) {}

    create(heartRate: IHeartRateMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(heartRate);
        return this.http
            .post<IHeartRateMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(heartRate: IHeartRateMySuffix): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(heartRate);
        return this.http
            .put<IHeartRateMySuffix>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IHeartRateMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IHeartRateMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    private convertDateFromClient(heartRate: IHeartRateMySuffix): IHeartRateMySuffix {
        const copy: IHeartRateMySuffix = Object.assign({}, heartRate, {
            dateTime: heartRate.dateTime != null && heartRate.dateTime.isValid() ? heartRate.dateTime.toJSON() : null
        });
        return copy;
    }

    private convertDateFromServer(res: EntityResponseType): EntityResponseType {
        res.body.dateTime = res.body.dateTime != null ? moment(res.body.dateTime) : null;
        return res;
    }

    private convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        res.body.forEach((heartRate: IHeartRateMySuffix) => {
            heartRate.dateTime = heartRate.dateTime != null ? moment(heartRate.dateTime) : null;
        });
        return res;
    }
}

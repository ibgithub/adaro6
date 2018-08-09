import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IVariableMySuffix } from 'app/shared/model/variable-my-suffix.model';

type EntityResponseType = HttpResponse<IVariableMySuffix>;
type EntityArrayResponseType = HttpResponse<IVariableMySuffix[]>;

@Injectable({ providedIn: 'root' })
export class VariableMySuffixService {
    private resourceUrl = SERVER_API_URL + 'api/variables';

    constructor(private http: HttpClient) {}

    create(variable: IVariableMySuffix): Observable<EntityResponseType> {
        return this.http.post<IVariableMySuffix>(this.resourceUrl, variable, { observe: 'response' });
    }

    update(variable: IVariableMySuffix): Observable<EntityResponseType> {
        return this.http.put<IVariableMySuffix>(this.resourceUrl, variable, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IVariableMySuffix>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IVariableMySuffix[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}

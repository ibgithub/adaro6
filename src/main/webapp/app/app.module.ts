import './vendor.ts';

import { NgModule, Injector } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { Ng2Webstorage, LocalStorageService, SessionStorageService } from 'ngx-webstorage';
import { JhiEventManager } from 'ng-jhipster';

import { AuthInterceptor } from 'app/blocks/interceptor/auth.interceptor';
import { AuthExpiredInterceptor } from 'app/blocks/interceptor/auth-expired.interceptor';
import { ErrorHandlerInterceptor } from 'app/blocks/interceptor/errorhandler.interceptor';
import { NotificationInterceptor } from 'app/blocks/interceptor/notification.interceptor';
import { Adaro6SharedModule } from 'app/shared';
import { Adaro6CoreModule } from 'app/core';
import { Adaro6AppRoutingModule } from 'app/app-routing.module';
import { Adaro6HomeModule } from 'app/home/home.module';
import { Adaro6AccountModule } from 'app/account/account.module';
import { Adaro6EntityModule } from 'app/entities/entity.module';
import { Adaro6DashboardModule } from 'app/dashboard/dashboard.module';
import { Adaro6AppHiThereModule } from 'app/hi-there/hi-there.module';
import { Adaro6MonitorModule } from 'app/monitor/monitor.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { JhiMainComponent, NavbarComponent, FooterComponent, PageRibbonComponent, ActiveMenuDirective, ErrorComponent } from 'app/layouts';

@NgModule({
    imports: [
        BrowserModule,
        Adaro6AppRoutingModule,
        Ng2Webstorage.forRoot({ prefix: 'jhi', separator: '-' }),
        Adaro6SharedModule,
        Adaro6CoreModule,
        Adaro6HomeModule,
        Adaro6AccountModule,
        Adaro6EntityModule,
        Adaro6DashboardModule,
        Adaro6AppHiThereModule,
        Adaro6MonitorModule,
        // jhipster-needle-angular-add-module JHipster will add new module here
    ],
    declarations: [JhiMainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
    providers: [
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthInterceptor,
            multi: true,
            deps: [LocalStorageService, SessionStorageService]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: AuthExpiredInterceptor,
            multi: true,
            deps: [Injector]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: ErrorHandlerInterceptor,
            multi: true,
            deps: [JhiEventManager]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: NotificationInterceptor,
            multi: true,
            deps: [Injector]
        }
    ],
    bootstrap: [JhiMainComponent]
})
export class Adaro6AppModule {}

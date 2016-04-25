import {Component} from 'angular2/core';
import {RouteConfig, ROUTER_DIRECTIVES, ROUTER_PROVIDERS} from 'angular2/router';
import {CustomerComponent} from './customers/customers.component';
import {CreateCustomerComponent} from './customers/create.customer.component';
import {CompanyComponent} from './companies/companies.component';
import {ShareComponent} from './shares/shares.component';
import {TransactionComponent} from './transactions/transactions.component';
import {AuthenticateComponent} from './authenticate/authenticate.component';
import {CustomerService} from './customers/customer.service';
import {CompanyService} from './companies/company.service';
import {ShareService} from './shares/share.service';
import {TransactionService} from './transactions/transaction.service';
import {AuthenticateService} from './authenticate/authenticate.service';
import {bootstrap} from 'angular2/platform/browser';
import {GlobalService} from './common/global.service';


@Component({
    selector: 'my-app',
    template: `
    <h1>{{title}}</h1>
    <nav>
      <a [routerLink]="['Authenticate']">Authenticate</a>
      <a [routerLink]="['Customers']">Customers</a>
      <a [routerLink]="['Companies']">Companies</a>
      <a [routerLink]="['Shares']">Shares</a>
      <a [routerLink]="['Transactions']">Transactions</a>
    </nav>
    <router-outlet></router-outlet>
  `,
    styleUrls: ['app/css/bootstrap.css', 'app/css/style.css', 'app/css/font-awesome.min.css', 'app/css/styleSwitcher.css', 'app/css/themes/green.css', 'app/css/themes/red.css'],
    directives: [ROUTER_DIRECTIVES],
    providers: [CustomerService, CompanyService, ShareService, TransactionService, AuthenticateService, GlobalService, ROUTER_PROVIDERS]
})
@RouteConfig([
    {path: '/authenticate', name: 'Authenticate', component: AuthenticateComponent},
    {path: '/customers', name: 'Customers', component: CustomerComponent},
    {path: '/companies', name: 'Companies', component: CompanyComponent},
    {path: '/shares', name: 'Shares', component: ShareComponent},
    {path: '/transactions', name: 'Transactions', component: TransactionComponent},
    {path: '/customers/create', name: 'CreateCustomer', component: CreateCustomerComponent},
])
export class AppComponent {
    public title = 'Trading Service';
}

// bootstrap(AppComponent, [GlobalService]);
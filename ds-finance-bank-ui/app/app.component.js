System.register(['angular2/core', 'angular2/router', './customers/customers.component', './customers/create.customer.component', './companies/companies.component', './shares/shares.component', './transactions/transactions.component', './authenticate/authenticate.component', './customers/customer.service', './companies/company.service', './shares/share.service', './transactions/transaction.service', './authenticate/authenticate.service', './common/global.service'], function(exports_1, context_1) {
    "use strict";
    var __moduleName = context_1 && context_1.id;
    var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
        var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
        if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
        else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
        return c > 3 && r && Object.defineProperty(target, key, r), r;
    };
    var __metadata = (this && this.__metadata) || function (k, v) {
        if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
    };
    var core_1, router_1, customers_component_1, create_customer_component_1, companies_component_1, shares_component_1, transactions_component_1, authenticate_component_1, customer_service_1, company_service_1, share_service_1, transaction_service_1, authenticate_service_1, global_service_1;
    var AppComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (customers_component_1_1) {
                customers_component_1 = customers_component_1_1;
            },
            function (create_customer_component_1_1) {
                create_customer_component_1 = create_customer_component_1_1;
            },
            function (companies_component_1_1) {
                companies_component_1 = companies_component_1_1;
            },
            function (shares_component_1_1) {
                shares_component_1 = shares_component_1_1;
            },
            function (transactions_component_1_1) {
                transactions_component_1 = transactions_component_1_1;
            },
            function (authenticate_component_1_1) {
                authenticate_component_1 = authenticate_component_1_1;
            },
            function (customer_service_1_1) {
                customer_service_1 = customer_service_1_1;
            },
            function (company_service_1_1) {
                company_service_1 = company_service_1_1;
            },
            function (share_service_1_1) {
                share_service_1 = share_service_1_1;
            },
            function (transaction_service_1_1) {
                transaction_service_1 = transaction_service_1_1;
            },
            function (authenticate_service_1_1) {
                authenticate_service_1 = authenticate_service_1_1;
            },
            function (global_service_1_1) {
                global_service_1 = global_service_1_1;
            }],
        execute: function() {
            AppComponent = (function () {
                function AppComponent() {
                    this.title = 'Trading Service';
                }
                AppComponent = __decorate([
                    core_1.Component({
                        selector: 'my-app',
                        template: "\n    <h1>{{title}}</h1>\n    <nav>\n      <a [routerLink]=\"['Authenticate']\">Authenticate</a>\n      <a [routerLink]=\"['Customers']\">Customers</a>\n      <a [routerLink]=\"['Companies']\">Companies</a>\n      <a [routerLink]=\"['Shares']\">Shares</a>\n      <a [routerLink]=\"['Transactions']\">Transactions</a>\n    </nav>\n    <router-outlet></router-outlet>\n  ",
                        styleUrls: ['app/css/bootstrap.css', 'app/css/style.css', 'app/css/font-awesome.min.css', 'app/css/styleSwitcher.css', 'app/css/themes/green.css', 'app/css/themes/red.css'],
                        directives: [router_1.ROUTER_DIRECTIVES],
                        providers: [customer_service_1.CustomerService, company_service_1.CompanyService, share_service_1.ShareService, transaction_service_1.TransactionService, authenticate_service_1.AuthenticateService, global_service_1.GlobalService, router_1.ROUTER_PROVIDERS]
                    }),
                    router_1.RouteConfig([
                        { path: '/authenticate', name: 'Authenticate', component: authenticate_component_1.AuthenticateComponent },
                        { path: '/customers', name: 'Customers', component: customers_component_1.CustomerComponent },
                        { path: '/companies', name: 'Companies', component: companies_component_1.CompanyComponent },
                        { path: '/shares', name: 'Shares', component: shares_component_1.ShareComponent },
                        { path: '/transactions', name: 'Transactions', component: transactions_component_1.TransactionComponent },
                        { path: '/customers/create', name: 'CreateCustomer', component: create_customer_component_1.CreateCustomerComponent },
                    ]), 
                    __metadata('design:paramtypes', [])
                ], AppComponent);
                return AppComponent;
            }());
            exports_1("AppComponent", AppComponent);
        }
    }
});
// bootstrap(AppComponent, [GlobalService]); 
//# sourceMappingURL=app.component.js.map
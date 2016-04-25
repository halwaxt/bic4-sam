System.register(['angular2/core', 'angular2/router', './customer.service', 'angular2/http', '../common/global.service'], function(exports_1, context_1) {
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
    var core_1, router_1, customer_service_1, http_1, global_service_1;
    var CreateCustomerComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (customer_service_1_1) {
                customer_service_1 = customer_service_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (global_service_1_1) {
                global_service_1 = global_service_1_1;
            }],
        execute: function() {
            CreateCustomerComponent = (function () {
                function CreateCustomerComponent(_customerService, _router, http, _globalService) {
                    this._customerService = _customerService;
                    this._router = _router;
                    this.http = http;
                    this._globalService = _globalService;
                    this.hasAccess = true; // _globalService.isAuthenticated && _globalService.isEmployee;
                }
                CreateCustomerComponent.prototype.doCreateCustomer = function (email, password, isEmployee) {
                    var _this = this;
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    headers.append('sessionId', this._globalService.sessionId);
                    var employee = 'false';
                    console.log("isEmployee=", isEmployee);
                    if (isEmployee.value == 'X') {
                        employee = 'true';
                    }
                    this.http.post('http://localhost:8080/ds-finance-bank-web/register?kundenname=' + email.value + '&password=' + password.value
                        + '&username=' + email.value + "&isEmployee=" + employee, {
                        headers: headers
                    })
                        .subscribe(function (data) {
                        console.log("data=", data._body);
                        _this.customerCreationDone = true;
                        _this.customerCreationFailed = false;
                    }, function (err) {
                        console.log('ERROR');
                        _this.customerCreationDone = false;
                        _this.customerCreationFailed = true;
                    }, function () { return console.log('Authentication Complete'); });
                };
                CreateCustomerComponent.prototype.ngOnInit = function () {
                    this.customerCreationDone = false;
                    this.customerCreationFailed = false;
                };
                CreateCustomerComponent.prototype.gotoDetail = function () {
                };
                CreateCustomerComponent.prototype.onSelect = function (customer) {
                };
                CreateCustomerComponent = __decorate([
                    core_1.Component({
                        selector: 'my-customer-create',
                        templateUrl: 'app/customers/create.customer.component.html',
                        styleUrls: ['app/css/bootstrap.css', 'app/css/style.css', 'app/css/font-awesome.min.css', 'app/css/styleSwitcher.css', 'app/css/themes/green.css', 'app/css/themes/red.css']
                    }), 
                    __metadata('design:paramtypes', [customer_service_1.CustomerService, router_1.Router, http_1.Http, global_service_1.GlobalService])
                ], CreateCustomerComponent);
                return CreateCustomerComponent;
            }());
            exports_1("CreateCustomerComponent", CreateCustomerComponent);
        }
    }
});
//# sourceMappingURL=create.customer.component.js.map
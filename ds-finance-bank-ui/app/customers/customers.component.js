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
    var CustomerComponent;
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
            CustomerComponent = (function () {
                function CustomerComponent(_customerService, _router, http, _globalService) {
                    this._customerService = _customerService;
                    this._router = _router;
                    this.http = http;
                    this._globalService = _globalService;
                    this.hasAccess = _globalService.isAuthenticated && _globalService.isEmployee;
                }
                CustomerComponent.prototype.onSearch = function (search) {
                    if (search.value) {
                        search.value = search.value.toLowerCase();
                        this.effectiveCustomers = [];
                        for (var i = 0; i < this.customers.length; i++) {
                            if (this.customers[i].name.toLowerCase().indexOf(search.value) > -1) {
                                this.effectiveCustomers.push(this.customers[i]);
                            }
                        }
                    }
                    else {
                        this.effectiveCustomers = this.customers;
                    }
                };
                CustomerComponent.prototype.getCustomers = function () {
                    var _this = this;
                    this.selectedCustomer = undefined;
                    this.customers = [];
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    headers.append('Access-Control-Allow-Origin', '*');
                    headers.append('sessionId', this._globalService.sessionId);
                    this.http.get('http://localhost:8080/ds-finance-bank-web/customers', {
                        headers: headers
                    })
                        .subscribe(function (data) {
                        _this.customers = JSON.parse(data._body);
                        _this.effectiveCustomers = _this.customers;
                    }, function (err) { return console.log('ERROR'); });
                    return this.customers;
                };
                CustomerComponent.prototype.ngOnInit = function () {
                    if (this._globalService.overwrittenCustomerId) {
                        this.isOtherCustomer = true;
                    }
                    this.getCustomers();
                };
                CustomerComponent.prototype.gotoDetail = function () {
                    // this._router.navigate(['CustomerDetail', {id: this.selectedCustomer.id}]);
                };
                CustomerComponent.prototype.onSelect = function (customer) {
                    if (!customer) {
                        this.isOtherCustomer = false;
                        this._globalService.resetCustomer();
                    }
                    else {
                        this.isOtherCustomer = true;
                        this._globalService.overwrittenCustomerId = customer.id;
                        this._globalService.overwrittenCustomerName = customer.name;
                    }
                };
                CustomerComponent = __decorate([
                    core_1.Component({
                        selector: 'my-customer',
                        templateUrl: 'app/customers/customers.component.html',
                        styleUrls: ['app/css/bootstrap.css', 'app/css/style.css', 'app/css/font-awesome.min.css', 'app/css/styleSwitcher.css', 'app/css/themes/green.css', 'app/css/themes/red.css']
                    }), 
                    __metadata('design:paramtypes', [customer_service_1.CustomerService, router_1.Router, http_1.Http, global_service_1.GlobalService])
                ], CustomerComponent);
                return CustomerComponent;
            }());
            exports_1("CustomerComponent", CustomerComponent);
        }
    }
});
//# sourceMappingURL=customers.component.js.map
System.register(['angular2/core', 'angular2/router', './company.service', 'angular2/http', '../common/global.service'], function(exports_1, context_1) {
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
    var core_1, router_1, company_service_1, http_1, global_service_1;
    var CompanyComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (company_service_1_1) {
                company_service_1 = company_service_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (global_service_1_1) {
                global_service_1 = global_service_1_1;
            }],
        execute: function() {
            CompanyComponent = (function () {
                function CompanyComponent(_companyService, _router, http, _globalService) {
                    this._companyService = _companyService;
                    this._router = _router;
                    this.http = http;
                    this._globalService = _globalService;
                    this.hasAccess = _globalService.isAuthenticated;
                }
                CompanyComponent.prototype.getEffectiveCompanies = function () {
                    this.getCompanies();
                    return this.effectiveCompanies;
                };
                CompanyComponent.prototype.onSearch = function (search) {
                    if (search.value) {
                        search.value = search.value.toLowerCase();
                        this.effectiveCompanies = [];
                        for (var i = 0; i < this.companies.length; i++) {
                            if (this.companies[i].symbol.toLowerCase().indexOf(search.value) > -1 || this.companies[i].name.toLowerCase().indexOf(search.value) > -1) {
                                this.effectiveCompanies.push(this.companies[i]);
                            }
                        }
                    }
                    else {
                        this.effectiveCompanies = this.companies;
                    }
                };
                CompanyComponent.prototype.getCompanies = function () {
                    var _this = this;
                    this.selectedCompany = undefined;
                    this.companies = [];
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    headers.append('Access-Control-Allow-Origin', '*');
                    headers.append('sessionId', this._globalService.sessionId);
                    // this.http.get('http://localhost:3099/companies', {
                    this.http.get('http://localhost:8080/ds-finance-bank-web/companies', {
                        headers: headers
                    })
                        .subscribe(function (data) {
                        _this.companies = JSON.parse(data._body);
                        _this.effectiveCompanies = _this.companies;
                    }, function (err) { return console.log('ERROR'); });
                    return this.companies;
                };
                CompanyComponent.prototype.ngOnInit = function () {
                    this.getCompanies();
                };
                CompanyComponent.prototype.gotoDetail = function () {
                    // this._router.navigate(['CompanyDetail', {id: this.selectedCompany.id}]);
                };
                CompanyComponent.prototype.buyOrSell = function (numberOfShares, action) {
                    var _this = this;
                    if (action) {
                        console.log(action.value + " " + numberOfShares.value + " number of pieces of " + this.selectedCompany.name);
                        var headers = new http_1.Headers();
                        headers.append('Content-Type', 'application/json');
                        headers.append('sessionId', this._globalService.sessionId);
                        headers.append('Access-Control-Request-Headers', 'Content-Type, X-Requested-With');
                        // this.http.post('http://localhost:3099/companies/' + this.selectedCompany.id + "?action="
                        // + action.value + "&numbers=" + numberOfShares.value, {
                        this.http.get('http://localhost:8080/ds-finance-bank-web/transaction?symbol=' + this.selectedCompany.symbol + "&action="
                            + action.value + "&numbers=" + numberOfShares.value + "&ID=" + this._globalService.getEffectiveCustomer(), {
                            headers: headers
                        })
                            .subscribe(function (data) {
                            _this.transactionDone = true;
                            console.log(JSON.parse(data._body));
                        }, function (err) {
                            _this.transactionFailed = true;
                            console.log('ERROR');
                        });
                    }
                };
                CompanyComponent.prototype.onSelect = function (company) {
                    if (this.hasAccess) {
                        this.transactionDone = false;
                        this.transactionFailed = false;
                        this.selectedCompany = company;
                    }
                };
                CompanyComponent = __decorate([
                    core_1.Component({
                        selector: 'my-company',
                        templateUrl: 'app/companies/companies.component.html',
                        styleUrls: ['app/css/bootstrap.css', 'app/css/style.css', 'app/css/font-awesome.min.css', 'app/css/styleSwitcher.css', 'app/css/themes/green.css', 'app/css/themes/red.css']
                    }), 
                    __metadata('design:paramtypes', [company_service_1.CompanyService, router_1.Router, http_1.Http, global_service_1.GlobalService])
                ], CompanyComponent);
                return CompanyComponent;
            }());
            exports_1("CompanyComponent", CompanyComponent);
        }
    }
});
//# sourceMappingURL=companies.component.js.map
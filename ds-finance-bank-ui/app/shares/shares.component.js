System.register(['angular2/core', 'angular2/router', './share.service', 'angular2/http', '../common/global.service'], function(exports_1, context_1) {
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
    var core_1, router_1, share_service_1, http_1, global_service_1;
    var ShareComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (share_service_1_1) {
                share_service_1 = share_service_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (global_service_1_1) {
                global_service_1 = global_service_1_1;
            }],
        execute: function() {
            ShareComponent = (function () {
                function ShareComponent(_shareService, _router, http, _globalService) {
                    this._shareService = _shareService;
                    this._router = _router;
                    this.http = http;
                    this._globalService = _globalService;
                    this.hasAccess = _globalService.isAuthenticated;
                }
                ShareComponent.prototype.getEffectiveShares = function () {
                    this.getShares();
                    return this.effectiveShares;
                };
                ShareComponent.prototype.onSearch = function (search) {
                    if (search.value) {
                        search.value = search.value.toLowerCase();
                        this.effectiveShares = [];
                        for (var i = 0; i < this.shares.length; i++) {
                            if (this.shares[i].company.symbol.toLowerCase().indexOf(search.value) > -1 || this.shares[i].company.name.toLowerCase().indexOf(search.value) > -1) {
                                this.effectiveShares.push(this.shares[i]);
                            }
                        }
                    }
                    else {
                        this.effectiveShares = this.shares;
                    }
                };
                ShareComponent.prototype.getShares = function () {
                    var _this = this;
                    this.selectedShare = undefined;
                    this.shares = [];
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    headers.append('Access-Control-Allow-Origin', '*');
                    headers.append('sessionId', this._globalService.sessionId);
                    this.http.get('http://localhost:8080/ds-finance-bank-web/portfolio?ID=' + this._globalService.getEffectiveCustomer(), {
                        headers: headers
                    })
                        .subscribe(function (data) {
                        _this.shares = JSON.parse(data._body);
                        _this.effectiveShares = _this.shares;
                    }, function (err) { return console.log('ERROR'); });
                    return this.shares;
                };
                ShareComponent.prototype.ngOnInit = function () {
                    this.name = this._globalService.getName();
                    this.getShares();
                };
                ShareComponent.prototype.gotoDetail = function () {
                    // this._router.navigate(['ShareDetail', { id: this.selectedShare.id }]);
                };
                ShareComponent.prototype.buyOrSell = function (numberOfShares, action) {
                    var _this = this;
                    if (action) {
                        console.log(action.value + " " + numberOfShares.value + " number of pieces of " + this.selectedShare.company.name);
                        var headers = new http_1.Headers();
                        headers.append('Content-Type', 'application/json');
                        headers.append('sessionId', this._globalService.sessionId);
                        // this.http.post('http://localhost:3099/companies/' + this.selectedCompany.id + "?action="
                        // + action.value + "&numbers=" + numberOfShares.value, {
                        this.http.get('http://localhost:8080/ds-finance-bank-web/transaction?symbol=' + this.selectedShare.company.symbol + "&action="
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
                ShareComponent.prototype.onSelect = function (share) {
                    if (this.hasAccess) {
                        this.transactionDone = false;
                        this.transactionFailed = false;
                        this.selectedShare = share;
                    }
                };
                ShareComponent = __decorate([
                    core_1.Component({
                        selector: 'my-share',
                        templateUrl: 'app/shares/shares.component.html',
                        styleUrls: ['app/css/bootstrap.css', 'app/css/style.css', 'app/css/font-awesome.min.css', 'app/css/styleSwitcher.css', 'app/css/themes/green.css', 'app/css/themes/red.css']
                    }), 
                    __metadata('design:paramtypes', [share_service_1.ShareService, router_1.Router, http_1.Http, global_service_1.GlobalService])
                ], ShareComponent);
                return ShareComponent;
            }());
            exports_1("ShareComponent", ShareComponent);
        }
    }
});
//# sourceMappingURL=shares.component.js.map
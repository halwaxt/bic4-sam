System.register(['angular2/core', 'angular2/router', './transaction.service', 'angular2/http', '../common/global.service'], function(exports_1, context_1) {
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
    var core_1, router_1, transaction_service_1, http_1, global_service_1;
    var TransactionComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (transaction_service_1_1) {
                transaction_service_1 = transaction_service_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (global_service_1_1) {
                global_service_1 = global_service_1_1;
            }],
        execute: function() {
            TransactionComponent = (function () {
                function TransactionComponent(_transactionService, _router, http, _globalService) {
                    this._transactionService = _transactionService;
                    this._router = _router;
                    this.http = http;
                    this._globalService = _globalService;
                    this.hasAccess = _globalService.isAuthenticated;
                }
                TransactionComponent.prototype.getEffectiveTransactions = function () {
                    this.getTransactions();
                    return this.effectiveTransactions;
                };
                TransactionComponent.prototype.onSearch = function (search) {
                    if (search.value) {
                        search.value = search.value.toLowerCase();
                        this.effectiveTransactions = [];
                        for (var i = 0; i < this.transactions.length; i++) {
                            if (this.transactions[i].company.symbol.toLowerCase().indexOf(search.value) > -1 || this.transactions[i].company.name.toLowerCase().indexOf(search.value) > -1) {
                                this.effectiveTransactions.push(this.transactions[i]);
                            }
                        }
                    }
                    else {
                        this.effectiveTransactions = this.transactions;
                    }
                };
                TransactionComponent.prototype.getTransactions = function () {
                    var _this = this;
                    this.transactions = [];
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    headers.append('Access-Control-Allow-Origin', '*');
                    headers.append('sessionId', this._globalService.sessionId);
                    this.http.get('http://localhost:8080/ds-finance-bank-web/customertrans?ID=' + this._globalService.getEffectiveCustomer(), {
                        headers: headers
                    })
                        .subscribe(function (data) {
                        _this.transactions = JSON.parse(data._body);
                        _this.effectiveTransactions = _this.transactions;
                    }, function (err) { return console.log('ERROR'); });
                    return this.transactions;
                };
                TransactionComponent.prototype.ngOnInit = function () {
                    this.name = this._globalService.getName();
                    this.getTransactions();
                };
                TransactionComponent.prototype.gotoDetail = function () {
                    // this._router.navigate(['ShareDetail', { id: this.selectedShare.id }]);
                };
                TransactionComponent = __decorate([
                    core_1.Component({
                        selector: 'my-transaction',
                        templateUrl: 'app/transactions/transactions.component.html',
                        styleUrls: ['app/css/bootstrap.css', 'app/css/style.css', 'app/css/font-awesome.min.css', 'app/css/styleSwitcher.css', 'app/css/themes/green.css', 'app/css/themes/red.css']
                    }), 
                    __metadata('design:paramtypes', [transaction_service_1.TransactionService, router_1.Router, http_1.Http, global_service_1.GlobalService])
                ], TransactionComponent);
                return TransactionComponent;
            }());
            exports_1("TransactionComponent", TransactionComponent);
        }
    }
});
//# sourceMappingURL=transactions.component.js.map
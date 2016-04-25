System.register(['angular2/core', 'angular2/router', './authenticate.service', 'angular2/http', '../common/global.service'], function(exports_1, context_1) {
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
    var core_1, router_1, authenticate_service_1, http_1, global_service_1;
    var AuthenticateComponent;
    return {
        setters:[
            function (core_1_1) {
                core_1 = core_1_1;
            },
            function (router_1_1) {
                router_1 = router_1_1;
            },
            function (authenticate_service_1_1) {
                authenticate_service_1 = authenticate_service_1_1;
            },
            function (http_1_1) {
                http_1 = http_1_1;
            },
            function (global_service_1_1) {
                global_service_1 = global_service_1_1;
            }],
        execute: function() {
            AuthenticateComponent = (function () {
                function AuthenticateComponent(_authenticateService, _router, http, _globalService) {
                    this._authenticateService = _authenticateService;
                    this._router = _router;
                    this.http = http;
                    this._globalService = _globalService;
                    this.isLoggedOut = !this._globalService.isAuthenticated;
                }
                AuthenticateComponent.prototype.doLogout = function () {
                    this._globalService.isAuthenticated = false;
                    this.isLoggedOut = true;
                    this._globalService.isEmployee = false;
                    this._globalService.sessionId = null;
                };
                AuthenticateComponent.prototype.doAuthenticate = function (email, password) {
                    var _this = this;
                    this.selectedAuthenticate = undefined;
                    this.authenticate = [];
                    var creds = JSON.stringify({ username: email.value, password: password.value });
                    var headers = new http_1.Headers();
                    headers.append('Content-Type', 'application/json');
                    this.http.post('http://localhost:8080/ds-finance-bank-web/authenticate?username=' + email.value + '&password=' + password.value, {
                        headers: headers
                    })
                        .subscribe(function (data) {
                        _this.authenticationFailed = false;
                        _this.authenticationDone = true;
                        console.log("data=", data.json());
                        _this._globalService.isAuthenticated = true;
                        _this.isLoggedOut = false;
                        _this._globalService.sessionId = data.json().sessionId;
                        _this._globalService.customerId = data.json().customer.id;
                        if (data.json().isEmployee) {
                            console.log("IS EMPLOYEE!");
                            _this._globalService.isEmployee = true;
                        }
                        else {
                            console.log("ISNT EMPLOYEE!");
                            _this._globalService.isEmployee = false;
                        }
                    }, function (err) {
                        console.log('ERROR');
                        _this._globalService.isAuthenticated = false;
                        _this.isLoggedOut = true;
                        _this.authenticationDone = false;
                        _this.authenticationFailed = true;
                    }, function () { return console.log('Authentication Complete'); });
                };
                AuthenticateComponent.prototype.gotoDetail = function () {
                    // this._router.navigate(['AuthenticateDetail', {id: this.selectedAuthenticate.id}]);
                };
                AuthenticateComponent.prototype.ngOnInit = function () {
                    this.authenticationDone = false;
                    this.authenticationFailed = false;
                };
                AuthenticateComponent.prototype.onSelect = function (authenticate) {
                    this.selectedAuthenticate = authenticate;
                };
                AuthenticateComponent = __decorate([
                    core_1.Component({
                        selector: 'my-authenticate',
                        templateUrl: 'app/authenticate/authenticate.component.html',
                        styleUrls: ['app/css/bootstrap.css', 'app/css/style.css', 'app/css/font-awesome.min.css', 'app/css/styleSwitcher.css', 'app/css/themes/green.css', 'app/css/themes/red.css']
                    }), 
                    __metadata('design:paramtypes', [authenticate_service_1.AuthenticateService, router_1.Router, http_1.Http, global_service_1.GlobalService])
                ], AuthenticateComponent);
                return AuthenticateComponent;
            }());
            exports_1("AuthenticateComponent", AuthenticateComponent);
        }
    }
});
//# sourceMappingURL=authenticate.component.js.map
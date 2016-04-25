import { Component, OnInit } from 'angular2/core';
import { Router } from 'angular2/router';
import { Authenticate, AuthenticateService } from './authenticate.service';
import { Http, Headers, HTTP_PROVIDERS } from 'angular2/http';
import { CustomerComponent } from '../customers/customers.component';
import {GlobalService} from '../common/global.service';

@Component({
    selector: 'my-authenticate',
    templateUrl: 'app/authenticate/authenticate.component.html',
    styleUrls: ['app/css/bootstrap.css', 'app/css/style.css', 'app/css/font-awesome.min.css', 'app/css/styleSwitcher.css', 'app/css/themes/green.css', 'app/css/themes/red.css']
})
export class AuthenticateComponent implements OnInit {
    public authenticate:Authenticate[];
    public selectedAuthenticate:Authenticate;
    email: string;
    password: string;
    authenticationDone: boolean;
    authenticationFailed: boolean;
    isLoggedOut: boolean;

    constructor(private _authenticateService:AuthenticateService, private _router:Router, public http:Http, public _globalService: GlobalService) {
        this.isLoggedOut = !this._globalService.isAuthenticated;
    }

    doLogout() {
        this._globalService.isAuthenticated = false;
        this.isLoggedOut = true;
        this._globalService.isEmployee = false;
        this._globalService.sessionId = null;
    }

    doAuthenticate(email, password) {
        this.selectedAuthenticate = undefined;
        this.authenticate = [];

        let creds = JSON.stringify({ username: email.value, password: password.value });

        let headers = new Headers();
        headers.append('Content-Type', 'application/json');

        this.http.post('http://localhost:8080/ds-finance-bank-web/authenticate?username=' + email.value + '&password=' + password.value, { // TODO: make this parameterized - the localhost:PORT part
                headers: headers
            })
            .subscribe(
                data => {
                    this.authenticationFailed = false;
                    this.authenticationDone = true;
                    console.log("data=", data.json());
                    this._globalService.isAuthenticated = true;
                    this.isLoggedOut = false;
                    this._globalService.sessionId = data.json().sessionId;
                    this._globalService.customerId = data.json().customer.id;
                    if(data.json().isEmployee) {
                        console.log("IS EMPLOYEE!");
                        this._globalService.isEmployee = true;
                    }
                    else {
                        console.log("ISNT EMPLOYEE!");
                        this._globalService.isEmployee = false;
                    }
                },
                err => {console.log('ERROR');
                    this._globalService.isAuthenticated = false;
                    this.isLoggedOut = true;
                    this.authenticationDone = false;
                    this.authenticationFailed = true;},
                () => console.log('Authentication Complete')
            );
    }

    gotoDetail() {
        // this._router.navigate(['AuthenticateDetail', {id: this.selectedAuthenticate.id}]);
    }

    ngOnInit() {
        this.authenticationDone = false;
        this.authenticationFailed = false;
    }

    onSelect(authenticate:Authenticate) {
        this.selectedAuthenticate = authenticate;
    }
}

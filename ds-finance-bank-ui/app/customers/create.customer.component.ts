import { Component, OnInit } from 'angular2/core';
import { Router } from 'angular2/router';
import { Customer, CustomerService } from './customer.service';
import { Http, Headers, HTTP_PROVIDERS } from 'angular2/http';
import {GlobalService} from '../common/global.service';

@Component({
    selector: 'my-customer-create',
    templateUrl: 'app/customers/create.customer.component.html',
    styleUrls: ['app/css/bootstrap.css', 'app/css/style.css', 'app/css/font-awesome.min.css', 'app/css/styleSwitcher.css', 'app/css/themes/green.css', 'app/css/themes/red.css']
})
export class CreateCustomerComponent implements OnInit {
    public customers:Customer[];
    public selectedCustomer:Customer;
    customerCreationDone: boolean;
    customerCreationFailed: boolean;
    public hasAccess: boolean;

    constructor(private _customerService:CustomerService, private _router:Router, public http:Http, public _globalService: GlobalService) {
        this.hasAccess = true; // _globalService.isAuthenticated && _globalService.isEmployee;
    }

    doCreateCustomer(email, password, isEmployee) {
        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('sessionId', this._globalService.sessionId);
        let employee = 'false';

        console.log("isEmployee=", isEmployee);

        if(isEmployee.value == 'X') {
            employee = 'true';
        }

        this.http.post('http://localhost:8080/ds-finance-bank-web/register?kundenname=' + email.value + '&password=' + password.value
                + '&username=' + email.value + "&isEmployee=" + employee, {
                headers: headers
            })
            .subscribe(
                data => {
                    console.log("data=", data._body);
                    this.customerCreationDone = true;
                    this.customerCreationFailed = false;
                },
                err => {console.log('ERROR');
                    this.customerCreationDone = false;
                    this.customerCreationFailed = true;},
                () => console.log('Authentication Complete')
            );
    }

    ngOnInit() {
        this.customerCreationDone = false;
        this.customerCreationFailed = false;
    }

    gotoDetail() {
    }

    onSelect(customer:Customer) {
    }
}

import { Component, OnInit } from 'angular2/core';
import { Router } from 'angular2/router';
import { Customer, CustomerService } from './customer.service';
import { Http, Headers, HTTP_PROVIDERS } from 'angular2/http';
import {GlobalService} from '../common/global.service';

@Component({
    selector: 'my-customer',
    templateUrl: 'app/customers/customers.component.html',
    styleUrls: ['app/css/bootstrap.css', 'app/css/style.css', 'app/css/font-awesome.min.css', 'app/css/styleSwitcher.css', 'app/css/themes/green.css', 'app/css/themes/red.css']
})
export class CustomerComponent implements OnInit {
    public customers:Customer[];
    public effectiveCustomers:Customer[];
    public selectedCustomer:Customer;
    public hasAccess: boolean;
    public isOtherCustomer: boolean;

    constructor(private _customerService:CustomerService, private _router:Router, public http:Http, public _globalService: GlobalService) {
        this.hasAccess = _globalService.isAuthenticated && _globalService.isEmployee;
    }

    onSearch(search) {

        if(search.value) {
            search.value = search.value.toLowerCase();
            this.effectiveCustomers = [];
            for (var i = 0; i < this.customers.length; i++) {
                if(this.customers[i].name.toLowerCase().indexOf(search.value) > -1) {
                    this.effectiveCustomers.push(this.customers[i]);
                }
            }
        }
        else {
            this.effectiveCustomers = this.customers;
        }
    }

    getCustomers() {
        this.selectedCustomer = undefined;
        this.customers = [];

        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Access-Control-Allow-Origin', '*');
        headers.append('sessionId', this._globalService.sessionId);

        this.http.get('http://localhost:8080/ds-finance-bank-web/customers', {
                headers: headers
            })
            .subscribe(
                data => {
                    this.customers = JSON.parse(data._body);
                    this.effectiveCustomers = this.customers;
                },
                err => console.log('ERROR')
            );
        return this.customers;
    }

    ngOnInit() {
        if(this._globalService.overwrittenCustomerId) {
            this.isOtherCustomer = true;
        }
        this.getCustomers();
    }

    gotoDetail() {
        // this._router.navigate(['CustomerDetail', {id: this.selectedCustomer.id}]);
    }

    onSelect(customer:Customer) {
        if(!customer) {
            this.isOtherCustomer = false;
            this._globalService.resetCustomer();
        }
        else {
            this.isOtherCustomer = true;
            this._globalService.overwrittenCustomerId = customer.id;
            this._globalService.overwrittenCustomerName = customer.name;
        }
    }
}

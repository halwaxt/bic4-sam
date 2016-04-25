import { Component, OnInit, EventEmitter } from 'angular2/core';
import { Router } from 'angular2/router';
import { Company, CompanyService } from './company.service';
import { Http, Headers, HTTP_PROVIDERS } from 'angular2/http';
import {GlobalService} from '../common/global.service';

@Component({
    selector: 'my-company',
    templateUrl: 'app/companies/companies.component.html',
    styleUrls: ['app/css/bootstrap.css', 'app/css/style.css', 'app/css/font-awesome.min.css', 'app/css/styleSwitcher.css', 'app/css/themes/green.css', 'app/css/themes/red.css']
})
export class CompanyComponent implements OnInit {
    public companies:Company[];
    public effectiveCompanies:Company[];
    public selectedCompany:Company;
    public transactionDone:Boolean;
    public transactionFailed:Boolean;
    public hasAccess: boolean;

    getEffectiveCompanies() {
        this.getCompanies();
        return this.effectiveCompanies;
    }

    onSearch(search) {
        if(search.value) {
            search.value = search.value.toLowerCase();
            this.effectiveCompanies = [];
            for (var i = 0; i < this.companies.length; i++) {
                if(this.companies[i].symbol.toLowerCase().indexOf(search.value) > -1 || this.companies[i].name.toLowerCase().indexOf(search.value) > -1) {
                    this.effectiveCompanies.push(this.companies[i]);
                }
            }
        }
        else {
            this.effectiveCompanies = this.companies;
        }
    }

    constructor(private _companyService:CompanyService, private _router:Router, public http:Http, public _globalService: GlobalService) {
        this.hasAccess = _globalService.isAuthenticated;
    }

    getCompanies() {
        this.selectedCompany = undefined;
        this.companies = [];

        let headers = new Headers();
        headers.append('Content-Type', 'application/json');
        headers.append('Access-Control-Allow-Origin', '*');
        headers.append('sessionId', this._globalService.sessionId);

        // this.http.get('http://localhost:3099/companies', {
        this.http.get('http://localhost:8080/ds-finance-bank-web/companies', {
                headers: headers
            })
            .subscribe(
                data => {
                    this.companies = JSON.parse(data._body);
                    this.effectiveCompanies = this.companies;
                },
                err => console.log('ERROR')
            );
        return this.companies;
    }

    ngOnInit() {
        this.getCompanies();
    }

    gotoDetail() {
        // this._router.navigate(['CompanyDetail', {id: this.selectedCompany.id}]);
    }

    buyOrSell(numberOfShares, action) {
        if(action) {
            console.log(action.value + " " + numberOfShares.value + " number of pieces of " + this.selectedCompany.name);

            let headers = new Headers();
            headers.append('Content-Type', 'application/json');
            headers.append('sessionId', this._globalService.sessionId);
            headers.append('Access-Control-Request-Headers', 'Content-Type, X-Requested-With');
            // this.http.post('http://localhost:3099/companies/' + this.selectedCompany.id + "?action="
                // + action.value + "&numbers=" + numberOfShares.value, {

            this.http.get('http://localhost:8080/ds-finance-bank-web/transaction?symbol=' + this.selectedCompany.symbol + "&action="
             + action.value + "&numbers=" + numberOfShares.value + "&ID=" + this._globalService.getEffectiveCustomer(), {
                    headers: headers
                })
                .subscribe(
                    data => {
                        this.transactionDone = true;
                        console.log(JSON.parse(data._body));
                    },
                    err => {
                        this.transactionFailed = true;
                        console.log('ERROR')
                    }
                );
        }
    }

    onSelect(company:Company) {
        if(this.hasAccess) {
            this.transactionDone = false;
            this.transactionFailed = false;
            this.selectedCompany = company;
        }
    }
}

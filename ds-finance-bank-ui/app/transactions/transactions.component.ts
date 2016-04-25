import { Component, OnInit } from 'angular2/core';
import { Router } from 'angular2/router';
import { Transaction, TransactionService } from './transaction.service';
import { Http, Headers, HTTP_PROVIDERS } from 'angular2/http';
import {GlobalService} from '../common/global.service';

@Component({
  selector: 'my-transaction',
  templateUrl: 'app/transactions/transactions.component.html',
  styleUrls: ['app/css/bootstrap.css', 'app/css/style.css', 'app/css/font-awesome.min.css', 'app/css/styleSwitcher.css', 'app/css/themes/green.css', 'app/css/themes/red.css']
})
export class TransactionComponent implements OnInit {
  public transactions: Transaction[];
  public effectiveTransactions:Transaction[];
  public transactionDone:Boolean;
  public transactionFailed:Boolean;
  public hasAccess: boolean;
  public name: String;

  getEffectiveTransactions() {
    this.getTransactions();
    return this.effectiveTransactions;
  }

  constructor(private _transactionService: TransactionService, private _router: Router, public http:Http, public _globalService: GlobalService) {
    this.hasAccess = _globalService.isAuthenticated;
  }

    onSearch(search) {

        if(search.value) {
            search.value = search.value.toLowerCase();
            this.effectiveTransactions = [];
            for (var i = 0; i < this.transactions.length; i++) {
                if(this.transactions[i].company.symbol.toLowerCase().indexOf(search.value) > -1 || this.transactions[i].company.name.toLowerCase().indexOf(search.value) > -1) {
                    this.effectiveTransactions.push(this.transactions[i]);
                }
            }
        }
        else {
            this.effectiveTransactions = this.transactions;
        }
    }


  getTransactions() {
    this.transactions = [];

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Access-Control-Allow-Origin', '*');
    headers.append('sessionId', this._globalService.sessionId);

    this.http.get('http://localhost:8080/ds-finance-bank-web/customertrans?ID=' + this._globalService.getEffectiveCustomer(), {
          headers: headers
        })
        .subscribe(
            data => {
              this.transactions = JSON.parse(data._body);
              this.effectiveTransactions = this.transactions;
            },
            err => console.log('ERROR')
        );
    return this.transactions;
  }

  ngOnInit() {
      this.name = this._globalService.getName();
    this.getTransactions();
  }

  gotoDetail() {
    // this._router.navigate(['ShareDetail', { id: this.selectedShare.id }]);
  }
}

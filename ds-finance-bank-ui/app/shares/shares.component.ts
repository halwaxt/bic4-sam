import { Component, OnInit } from 'angular2/core';
import { Router } from 'angular2/router';
import { Share, ShareService } from './share.service';
import { Http, Headers, HTTP_PROVIDERS } from 'angular2/http';
import {GlobalService} from '../common/global.service';

@Component({
  selector: 'my-share',
  templateUrl: 'app/shares/shares.component.html',
  styleUrls: ['app/css/bootstrap.css', 'app/css/style.css', 'app/css/font-awesome.min.css', 'app/css/styleSwitcher.css', 'app/css/themes/green.css', 'app/css/themes/red.css']
})
export class ShareComponent implements OnInit {
  public shares: Share[];
  public effectiveShares:Share[];
  public selectedShare: Share;
  public transactionDone:Boolean;
  public transactionFailed:Boolean;
  public hasAccess: boolean;
  public name: String;

  getEffectiveShares() {
    this.getShares();
    return this.effectiveShares;
  }

  constructor(private _shareService: ShareService, private _router: Router, public http:Http, public _globalService: GlobalService) {
    this.hasAccess = _globalService.isAuthenticated;
  }

    onSearch(search) {

        if(search.value) {
            search.value = search.value.toLowerCase();
            this.effectiveShares = [];
            for (var i = 0; i < this.shares.length; i++) {
                if(this.shares[i].company.symbol.toLowerCase().indexOf(search.value) > -1 || this.shares[i].company.name.toLowerCase().indexOf(search.value) > -1) {
                    this.effectiveShares.push(this.shares[i]);
                }
            }
        }
        else {
            this.effectiveShares = this.shares;
        }
    }


  getShares() {
    this.selectedShare = undefined;
    this.shares = [];

    let headers = new Headers();
    headers.append('Content-Type', 'application/json');
    headers.append('Access-Control-Allow-Origin', '*');
    headers.append('sessionId', this._globalService.sessionId);

    this.http.get('http://localhost:8080/ds-finance-bank-web/portfolio?ID=' + this._globalService.getEffectiveCustomer(), {
          headers: headers
        })
        .subscribe(
            data => {
              this.shares = JSON.parse(data._body);
              this.effectiveShares = this.shares;
            },
            err => console.log('ERROR')
        );
    return this.shares;
  }

  ngOnInit() {
    this.name = this._globalService.getName();
    this.getShares();
  }

  gotoDetail() {
    // this._router.navigate(['ShareDetail', { id: this.selectedShare.id }]);
  }

  buyOrSell(numberOfShares, action) {
    if(action) {
      console.log(action.value + " " + numberOfShares.value + " number of pieces of " + this.selectedShare.company.name);

      let headers = new Headers();
      headers.append('Content-Type', 'application/json');
      headers.append('sessionId', this._globalService.sessionId);
      // this.http.post('http://localhost:3099/companies/' + this.selectedCompany.id + "?action="
      // + action.value + "&numbers=" + numberOfShares.value, {

      this.http.get('http://localhost:8080/ds-finance-bank-web/transaction?symbol=' + this.selectedShare.company.symbol + "&action="
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

  onSelect(share: Share) {
    if(this.hasAccess) {
      this.transactionDone = false;
      this.transactionFailed = false;
      this.selectedShare = share;
    }
  }
}

import {Injectable} from 'angular2/core';

@Injectable()
export class GlobalService {
    isAuthenticated: boolean = false;
    isEmployee: boolean = false;
    sessionId: String;
    customerId: String;
    overwrittenCustomerId: String;
    overwrittenCustomerName: String

    public getEffectiveCustomer() {
        if(this.overwrittenCustomerId) {
            return this.overwrittenCustomerId;
        }
        else {
            return this.customerId;
        }
    }

    public getName() {
        if(this.overwrittenCustomerName) {
            return this.overwrittenCustomerName;
        }
        else {
            return 'YOU';
        }
    }

    public resetCustomer() {
        this.overwrittenCustomerId = null;
        this.overwrittenCustomerName = null;
    }
}
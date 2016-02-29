package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.Action;
import at.technikum.bic4a16.bi.model.Company;
import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.FinancialTransactionRequest;

class DefaultFinancialTransactionRequest implements FinancialTransactionRequest {

    private Company company;
    private Customer customer;
    private long numberOfShares;
    private Action action;

    @Override
    public Company getCompany() {
        return null;
    }

    @Override
    public Customer getCustomer() {
        return null;
    }

    @Override
    public long getNumberOfShares() {
        return 0;
    }

    @Override
    public Action getAction() {
        return null;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setNumberOfShares(long numberOfShares) {
        this.numberOfShares = numberOfShares;
    }

    public void setAction(Action action) {
        this.action = action;
    }
}

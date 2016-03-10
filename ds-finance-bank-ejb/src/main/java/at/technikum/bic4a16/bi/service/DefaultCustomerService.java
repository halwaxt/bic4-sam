package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.FinancialTransaction;
import at.technikum.bic4a16.bi.model.Stock;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

@Stateless
@PermitAll
public class DefaultCustomerService implements CustomerService{
    @Override
    public Customer getCustomer(int id) {
        return null;
    }

    @Override
    public Customer getCustomer(String name) {
        return null;
    }

    @Override
    public Customer[] getAllCustomers() {
        return new Customer[0];
    }

    @Override
    public Customer createCustomer(String name) {
        return null;
    }

    @Override
    public FinancialTransaction[] getTransactions(Customer customer) {
        return new FinancialTransaction[0];
    }

    @Override
    public Stock[] getPortfolio(Customer customer) {
        return new Stock[0];
    }
}

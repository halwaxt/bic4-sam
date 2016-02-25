package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.FinancialTransaction;
import at.technikum.bic4a16.bi.model.Stock;

/**
 * Created by Thomas on 25.02.16.
 */
public interface CustomerService {
    Customer getCustomer(int id);
    Customer[] getAllCustomers();
    Customer createCustomer(String name);
    FinancialTransaction[] getTransactions(Customer customer);
    Stock[] getPortfolio(Customer customer);
}

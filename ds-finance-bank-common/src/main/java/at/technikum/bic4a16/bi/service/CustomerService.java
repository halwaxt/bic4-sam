package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.FinancialTransaction;
import at.technikum.bic4a16.bi.model.Stock;

import javax.ejb.Stateless;

/**
 * Created by Thomas on 25.02.16.
 */
@Stateless
public interface CustomerService {
    // get a customer by his unique id
    Customer getCustomer(int id);

    // get a customer by his name (which is also unique)
    Customer getCustomer(String name);

    // get all customers
    Customer[] getAllCustomers();

    // create a customer by transmitting his name
    Customer createCustomer(String name);

    // get all financial transactions of a customer
    FinancialTransaction[] getTransactions(Customer customer);

    // the the portfolio of a customer
    Stock[] getPortfolio(Customer customer);
}

package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;

@Stateless
@Startup
@LocalBean
public class DefaultFinancialService implements FinancialService {


    private static final Logger LOG = LoggerFactory.getLogger(DefaultFinancialService.class);

    @Resource
    ManagedExecutorService managedExecutorService;

    @PostConstruct
    void initialize() {
        LOG.debug("Hi, I am initialized :-)");
    }

    @Override
    public FinancialTransaction submitTransaction(FinancialTransactionRequest request) {

        LOG.debug("submitting a transaction fro request " + request);

        DefaultFinancialTransaction financialTransaction = new DefaultFinancialTransaction();
        financialTransaction.setRequest(request);
        financialTransaction.setState(State.PENDING);

        // persist transaction first
        // using DAO

        //
        //managedExecutorService.execute(stockExchangeTransaction(null));


        return financialTransaction;
    }

    @Override
    public FinancialTransactionRequest createRequest(Customer customer, Company company, long shares, Action action) {
        DefaultFinancialTransactionRequest request = new DefaultFinancialTransactionRequest();
        request.setCustomer(customer);
        request.setCompany(company);
        request.setNumberOfShares(shares);
        request.setAction(action);
        //logger.fine("created financial transaction request");
        return request;
    }

    private Runnable stockExchangeTransaction(FinancialTransaction transaction) {
        return new Runnable() {
            @Override
            public void run() {
                // verify prerequisites
                // call stock exchange and perform a buy or sell operation
                // update and persist state
                // update and persist portfolio
                // update and persist bank balance
            }
        };
    }
}

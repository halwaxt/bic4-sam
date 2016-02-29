package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.*;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.logging.Logger;

@Stateful
@Default
public class DefaultFinancialService implements FinancialService {


    private Logger logger;

    /*
    @EJB
    private ManagedExecutorService managedExecutorService;
*/



    @Override
    public FinancialTransaction submitTransaction(FinancialTransactionRequest request) {
        // persist transaction first
        // using DAO



        //
        //managedExecutorService.execute(stockExchangeTransaction(null));


        return null;
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
                // call stock exchange and perform a buy or sell operation
                // update and persist state
                // update and persist portfolio
                // update and persist bank balance
            }
        };
    }
}

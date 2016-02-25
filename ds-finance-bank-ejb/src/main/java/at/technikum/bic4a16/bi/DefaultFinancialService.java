package at.technikum.bic4a16.bi;

import javax.annotation.Resource;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.io.Serializable;

/**
 * Created by Thomas on 24.02.16.
 */

@SessionScoped
@Default
public final class DefaultFinancialService implements FinancialService, Serializable{

    @Resource
    private ManagedExecutorService managedExecutorService;

    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("FinancialTransaction");

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

    @Override
    public FinancialTransaction executeTransaction(FinancialTransactionRequest request) {
        // persist transaction first
        final EntityManager entityManager = factory.createEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        {
            entityManager.persist(request);
        }
        transaction.commit();

        //
        managedExecutorService.execute(stockExchangeTransaction(null));


        return null;
    }
}

package at.technikum.bic4a16.bi;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import java.util.UUID;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * Created by Thomas on 24.02.16.
 */

@ApplicationScoped
public final class DefaultBankster implements Bankster{

    @Resource
    private ManagedExecutorService managedExecutorService;

    private final EntityManagerFactory factory = Persistence.createEntityManagerFactory("FinancialTransaction");


    @Override
    public UUID executeTransaction(FinancialTransaction financialTransaction) {

        // persist transaction first
        final EntityManager entityManager = factory.createEntityManager();
        final EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        {
            entityManager.persist(financialTransaction);
        }
        transaction.commit();

        //
        managedExecutorService.execute(stockExchangeTransaction(financialTransaction));


        return financialTransaction.getId();
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

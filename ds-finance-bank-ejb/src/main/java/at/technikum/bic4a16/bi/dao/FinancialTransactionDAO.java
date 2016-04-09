package at.technikum.bic4a16.bi.dao;

import at.technikum.bic4a16.bi.entity.CompanyEntity;
import at.technikum.bic4a16.bi.entity.CustomerEntity;
import at.technikum.bic4a16.bi.entity.FinancialTransactionEntity;
import at.technikum.bic4a16.bi.entity.StockEntity;
import at.technikum.bic4a16.bi.model.Stock;

import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@PermitAll
public class FinancialTransactionDAO {
    @PersistenceContext private EntityManager entityManager;
    
    public FinancialTransactionEntity findById(int id) {
        return entityManager.find(FinancialTransactionEntity.class, id);
    }

    public List<FinancialTransactionEntity> findByCustomer(CustomerEntity customer) {
        return entityManager.createQuery("FROM FinancialTransactionEntity t "+
                "WHERE t.customer = :customer ",
                FinancialTransactionEntity.class).setParameter("customer", customer).
                getResultList();
    }

    public List<FinancialTransactionEntity> findByCompany(CompanyEntity company) {
        return entityManager.createQuery("FROM FinancialTransactionEntity t "+
                "WHERE t.company = :company",
                FinancialTransactionEntity.class).setParameter("company", company).
                getResultList();
    }
    
    public void save(FinancialTransactionEntity transaction) {
        entityManager.persist(transaction);
    }

    public void update(FinancialTransactionEntity financialTransactionEntity) {
        // TODO:  find correct way to lock  //

        final FinancialTransactionEntity merge = entityManager.merge(financialTransactionEntity);
        entityManager.persist(merge);
    }

    public void delete(FinancialTransactionEntity transaction) {
        entityManager.remove(transaction);
    }


    public List<Stock> getPortfolio(CustomerEntity customer) {
        String nativeQuery =
        "SELECT rownum() as IDENTITY , * FROM (SELECT COMPANY_FK, -1* sum(actionvalue * numberofshares) as SHARES FROM TRANSACTION " +
        "WHERE customer_FK = :customer_id and state='COMPLETED' " +
        "GROUP BY company_fk " +
        "HAVING sum(actionvalue * numberofshares) < 0)";

        final Query query = entityManager.createNativeQuery(nativeQuery, StockEntity.class);
        query.setParameter("customer_id", customer.getId());
        return query.getResultList();
    }
}

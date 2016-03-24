package at.technikum.bic4a16.bi.dao;

import at.technikum.bic4a16.bi.entity.FinancialTransactionEntity;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@PermitAll
public class FinancialTransactionDAO {
    @PersistenceContext private EntityManager entityManager;
    
    public List<FinancialTransactionEntity> findByTransId(int TransId) {
        return entityManager.createQuery("FROM FinancialTransactionEntity t"+
                "WHERE t.TransId LIKE :partOfTransId ",
                FinancialTransactionEntity.class).setParameter("partOfTransId", "%"+TransId+"%").
                getResultList();
    }
    public List<FinancialTransactionEntity> findByCustomer(int customer) {
        return entityManager.createQuery("FROM FinancialTransactionEntity t"+
                "WHERE t.customer LIKE :partOfCustomer ",
                FinancialTransactionEntity.class).setParameter("partOfCustomer", "%"+customer+"%").
                getResultList();
    }
    
    public List<FinancialTransactionEntity> findBySymbol(int symbol) {
        return entityManager.createQuery("FROM FinancialTransactionEntity t"+
                "WHERE t.symbol LIKE :partOfSymbol ",
                FinancialTransactionEntity.class).setParameter("partOfSymbol", "%"+symbol+"%").
                getResultList();
    }
    
    public void persist(FinancialTransactionEntity transaction) {
        entityManager.persist(transaction);
    }

    public void delete(FinancialTransactionEntity transaction) {
        entityManager.remove(transaction);
    }
}

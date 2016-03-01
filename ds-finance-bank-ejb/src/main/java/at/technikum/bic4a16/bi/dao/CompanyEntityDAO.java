package at.technikum.bic4a16.bi.dao;

/**
 *
 * @author Romeo
 */

import at.technikum.bic4a16.bi.entity.CompanyEntity;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CompanyEntityDAO {
    @PersistenceContext private EntityManager entityManager;
    
    public CompanyEntity findBySymbol(String symbol) {
        return entityManager.find(CompanyEntity.class, symbol);
    }
    
    public List<CompanyEntity> findByName(String name) {
        return entityManager.createQuery("FROM Company c"+
                "WHERE c.name LIKE :partOfName ",
                CompanyEntity.class).setParameter("partOfName", "%"+name+"%").
                getResultList();
    }
    
    public void persist(CompanyEntity company) {
        entityManager.persist(company);
    }

    public void delete(CompanyEntity company) {
        entityManager.remove(company);
    }

}
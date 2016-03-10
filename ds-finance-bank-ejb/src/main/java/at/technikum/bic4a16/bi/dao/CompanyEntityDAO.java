package at.technikum.bic4a16.bi.dao;

/**
 *
 * @author Romeo
 */

import at.technikum.bic4a16.bi.entity.CompanyEntity;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@PermitAll
public class CompanyEntityDAO {
    @PersistenceContext private EntityManager entityManager;
    
    public CompanyEntity findBySymbol(String symbol) {
        return entityManager.find(CompanyEntity.class, symbol);
    }
    
    public List<CompanyEntity> findByName(String name) {
        return entityManager.createQuery("FROM Company c "+
                "WHERE c.name LIKE :partOfName ",
                CompanyEntity.class).setParameter("partOfName", "%"+name+"%").
                getResultList();
    }
    
    public void persist(CompanyEntity entity) {
        entityManager.persist(entity);
    }

    public void merge(CompanyEntity entity) {
        entityManager.merge(entity);
    }

    public void delete(CompanyEntity entity) {
        entityManager.remove(entity);
    }

}
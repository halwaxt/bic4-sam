package at.technikum.bic4a16.bi.dao;

/**
 *
 * @author Romeo
 */

import at.technikum.bic4a16.bi.entity.CompanyEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;

@Stateless
@PermitAll
public class CompanyEntityDAO {
    private static final int BATCH_SIZE = 50;

    @PersistenceContext private EntityManager entityManager;

    public CompanyEntity findBySymbol(String symbol) {
        return entityManager.find(CompanyEntity.class, symbol);
    }
    
    public List<CompanyEntity> findByName(String name) {

        return entityManager.createQuery("SELECT c FROM CompanyEntity c "+
                "WHERE c.name LIKE :partOfName ",
                CompanyEntity.class).setParameter("partOfName", name).
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


    public <T extends CompanyEntity> Collection<T> bulkSave(Collection<T> entities) {
        final List<T> savedEntities = new ArrayList<T>(entities.size());
        int i = 0;
        for (T t : entities) {
            savedEntities.add(persistOrMerge(t));
            i++;
            if (i % BATCH_SIZE == 0) {
                // Flush a batch of inserts and release memory.
                entityManager.flush();
                entityManager.clear();
            }
        }
        return savedEntities;
    }

    private <T extends CompanyEntity> T persistOrMerge(T t) {
        if (t.getSymbol() == null) {
            entityManager.persist(t);
            return t;
        } else {
            return entityManager.merge(t);
        }
    }


}
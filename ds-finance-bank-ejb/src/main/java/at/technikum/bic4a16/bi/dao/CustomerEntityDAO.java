/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.technikum.bic4a16.bi.dao;

import at.technikum.bic4a16.bi.entity.CustomerEntity;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Patrik
 */
@Stateless
@PermitAll
public class CustomerEntityDAO {
    @PersistenceContext private EntityManager entityManager;
    
    public CustomerEntity findById(String id) {
        return entityManager.find(CustomerEntity.class, id);
    }
    
    public List<CustomerEntity> findByName(String name) {
        return entityManager.createQuery("FROM CustomerEntity c"+
                "WHERE c.name LIKE :partOfName ",
                CustomerEntity.class).setParameter("partOfName", "%"+name+"%").
                getResultList();
    }
    
    public void persist(CustomerEntity customer) {
        entityManager.persist(customer);
    }

    public void delete(CustomerEntity customer) {
        entityManager.remove(customer);
    }
}

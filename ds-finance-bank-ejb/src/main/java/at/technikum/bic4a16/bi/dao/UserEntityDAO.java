package at.technikum.bic4a16.bi.dao;

import at.technikum.bic4a16.bi.entity.UserEntity;
import java.util.List;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@PermitAll
public class UserEntityDAO {
    @PersistenceContext private EntityManager entityManager;
    
    public UserEntity findByUserName(String username) {
        return entityManager.find(UserEntity.class, username);
    }
    
    public void persist(UserEntity user) {
        entityManager.persist(user);
    }

    public void delete(UserEntity user) {
        entityManager.remove(user);
    }
}

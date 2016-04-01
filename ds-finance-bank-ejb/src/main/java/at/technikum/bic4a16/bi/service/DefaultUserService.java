package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.User;
import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.dao.UserEntityDAO;
import at.technikum.bic4a16.bi.entity.UserEntity;
import at.technikum.bic4a16.bi.entity.CustomerEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;

/**
 * Created by Patrik on 27.03.2016.
 */
public class DefaultUserService implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUserService.class);

    @EJB
    private CustomerService customerService;

    @EJB
    private UserEntityDAO userEntityDAO;

    @Override
    public User createUser(String email, String password, Boolean isEmployee) {
        Customer customer = customerService.createCustomer(email);
        UserEntity userEntity = new UserEntity();
        userEntity.setCustomer((CustomerEntity) customer);
        userEntity.setUsername(email);
        userEntity.setPwhash(password);
        userEntity.setEmployee(isEmployee);

        userEntityDAO.persist(userEntity);

        LOGGER.info("persisted new user. username is now: " + userEntity.getUsername());

        return userEntity;
    }
}

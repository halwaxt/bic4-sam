package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.Customer;

import javax.ejb.Remote;
import at.technikum.bic4a16.bi.model.User;

/**
 * Created by Patrik on 27.03.2016.
 */

@Remote
public interface UserService {    // create a customer by transmitting his name
    User createUser(String email, String password, Boolean isEmployee);
}

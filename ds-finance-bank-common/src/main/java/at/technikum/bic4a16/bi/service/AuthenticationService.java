package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.User;

import javax.ejb.Stateless;

/**
 * Created by Thomas on 25.02.16.
 */

public interface AuthenticationService {
    // used to authenticate
    User authenticate(String username, String password);

    // used to create a new user
    User createUser(String username, String password, Boolean isEmployee);

    // used to update a user - e.g. for setting the corresponding customer
    void updateUser(User user);
}

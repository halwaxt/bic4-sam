package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.User;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

@Stateless
@PermitAll
public class DefaultAuthenticationService implements AuthenticationService{
    // used to authenticate
    @Override
    public User authenticate(String username, String password) {
        return null;
    }

    // used to create a new user
    @Override
    public User createUser(String username, String password, Boolean isEmployee) {
        return null;
    }

    // used to update a user - e.g. for setting the corresponding customer
    @Override
    public void updateUser(User user) {

    }
}

package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.User;

/**
 * Created by Thomas on 25.02.16.
 */
public interface AuthenticationService {
    User authenticate(String username, String password);
    User createUser(String username, String password);
    void setPassword(User user);
}

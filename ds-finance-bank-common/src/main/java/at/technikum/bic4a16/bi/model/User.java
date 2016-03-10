package at.technikum.bic4a16.bi.model;

import javax.ejb.Remote;

/**
 * Created by Thomas on 25.02.16.
 */

@Remote
public interface User {
    // username - e.g. jordan.belfort
    String getUsername();

    void setUsername(String username);

    // customer - e.g. Customer with id 1
    Customer getCustomer();

    void setCustomer(Customer customer);

    // defines if the user is an employee or not - e.g. true
    boolean isEmployee();

    void setIsEmployee(Boolean isEmployee);

    // sessionId, which has to be transmitted to authenticate - e.g. $2a$05$Lhay
    String getSessionId();

    void setSessionId(String sessionId);
}

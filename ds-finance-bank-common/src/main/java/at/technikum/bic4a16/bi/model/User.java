package at.technikum.bic4a16.bi.model;

import javax.ejb.Remote;
import java.io.Serializable;

/**
 * Created by Thomas on 25.02.16.
 */

@Remote
public interface User extends Serializable{
    // username - e.g. jordan.belfort
    String getUsername();



    // customer - e.g. Customer with id 1
    Customer getCustomer();



    // defines if the user is an employee or not - e.g. true
    boolean getIsEmployee();



    // sessionId, which has to be transmitted to authenticate - e.g. $2a$05$Lhay
    String getSessionId();

    void setSessionId(String sessionId);


}

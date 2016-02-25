package at.technikum.bic4a16.bi;

/**
 * Created by Thomas on 25.02.16.
 */
public interface User {
    String getUsername();
    Customer getCustomer();
    void setCustomer(Customer customer);
    boolean isEmployee();
}

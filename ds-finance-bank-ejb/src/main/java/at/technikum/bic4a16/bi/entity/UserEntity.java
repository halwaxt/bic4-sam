package at.technikum.bic4a16.bi.entity;

/**
 * @author Romeo
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import java.io.Serializable;
import javax.persistence.JoinColumn;

import at.technikum.bic4a16.bi.model.User;
import at.technikum.bic4a16.bi.model.Customer;

@Entity
@Table(name = "USER")
public class UserEntity implements User, Serializable {
    @Id
    private String username;
    @OneToOne

    @JoinColumn(name = "CUSTOMER_FK")
    private CustomerEntity customer;
    private boolean employee;
    private String pwhash;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        // this.customer = (Customer) customer;
    }

    public boolean getEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }

    public String getPwhash() {
        return pwhash;
    }

    public void setPwhash(String pwhash) {
        this.pwhash = pwhash;
    }

    public String getSessionId() {
        return "";
    }

    public void setSessionId(String sessionId) {
    }
}

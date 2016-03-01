package at.technikum.bic4a16.bi.entity;

/**
 *
 * @author Romeo
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import java.io.Serializable;
import javax.persistence.JoinColumn;


@Entity
@Table(name="USER")
public class UserEntity implements Serializable{
    @Id
    private String username;
    @OneToOne

    @JoinColumn(name="CUSTOMER_FK")private CustomerEntity customer;
    private boolean employee;
    private char pwhash;
    
    
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }   
    public CustomerEntity getCustomer(){
        return customer;
    }
    public void setCustomer(CustomerEntity customer){
        this.customer = customer;
    }
    public boolean getEmployee(){
        return employee;
    }
    public void setEmployee(boolean employee){
        this.employee = employee;
    }
    public char getPwhash(){
        return pwhash;
    }
    public void setÜwhash(char pwhash){
        this.pwhash = pwhash;
    }

}

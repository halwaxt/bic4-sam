package at.technikum.bic4a16.bi.entity;

/**
 *
 * @author Romeo
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="USER")
public class UserEntity implements Serializable{
    @Id
    String username;
    int customer;
    boolean employee;
    char pwhash;
    
    public String getUsername(){
        return username;
    }
    public void setUsername(String username){
        this.username = username;
    }   
    public int getCustomer(){
        return customer;
    }
    public void setCustomer(int customer){
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

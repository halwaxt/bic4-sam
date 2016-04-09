package at.technikum.bic4a16.bi.entity;

/**
 *
 * @author Romeo
 */
import at.technikum.bic4a16.bi.model.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import java.io.Serializable;
import javax.persistence.JoinColumn;


@Entity
@Table(name="USER")
public class UserEntity implements User, Serializable{
    @Id
    private String username;
    @OneToOne

    @JoinColumn(name="CUSTOMER_FK")
    private CustomerEntity customer;
    private boolean isEmployee;
    private String pwhash;
    public String sessionId;
    
    
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
    public boolean getIsEmployee(){
        return isEmployee;
    }
    public void setIsEmployee(boolean isEmployee){
        this.isEmployee = isEmployee;
    }
    public String getPwhash(){
        return pwhash;
    }
    public void setPwhash(String pwhash){
        this.pwhash = pwhash;
    }
    public String getSessionId(){ return sessionId;}
    public void setSessionId(String sessionId){ this.sessionId=sessionId;}



}

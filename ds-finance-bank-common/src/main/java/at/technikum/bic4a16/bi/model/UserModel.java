/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.technikum.bic4a16.bi.model;

/**
 *
 * @author Patrik
 */
public class UserModel implements User {

    private String username;
    private Customer customer;
    private Boolean isEmployee;
    private String sessionId;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return this.customer;
    }
    
    public void setIsEmployee(Boolean isEmployee) {
        this.isEmployee = isEmployee;
    }

    public boolean isEmployee() {
        return this.isEmployee;
    }
    
    public void setSessionId(String sessionId){
        this.sessionId = sessionId;
    }
    
    public String getSessionId() {
        return this.sessionId;
    }

}

package at.technikum.bic4a16.bi.entity;

/**
 *
 * @author Romeo
 */

import javax.persistence.*;

import at.technikum.bic4a16.bi.model.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name="TRANSACTION")
public class FinancialTransactionEntity implements FinancialTransaction, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="CUSTOMER_FK")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name="COMPANY_FK")
    private CompanyEntity company;

    private LocalDateTime date;
    private int numberOfShares;

    @Enumerated(EnumType.STRING)
    private State state;
    
    private double price;
    private int actionValue;

    private String message;

    public int getId(){
        return id;
    }
    public void setId(int TransId){
        this.id = TransId;
    }

    @Override
    public Company getCompany() {
        return this.company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    @Override
    public Customer getCustomer(){
        return customer;
    }
    public void setCustomer(CustomerEntity customer){
        this.customer = customer;
    }

    @Override
    public int getNumberOfShares(){
        return numberOfShares;
    }

    public void setNumberOfShares(int numberOfShares){
        this.numberOfShares = numberOfShares;
    }

    @Override
    public Action getAction() {
        return Action.parse(this.actionValue);
    }

    @Override
    public String getMessage() { return this.message; }
    public void setMessage(String message) { this.message = message; }

    public void setAction(Action action) {
        this.actionValue = action.getActionValue();
    }

    public LocalDateTime getDate(){ return date; }
    public void setDate(LocalDateTime date){ this.date = date; }

    public State getState(){ return state; }
    public void setState(State state){
        this.state = state;
    }

    public double getPrice(){
        return price;
    }
    public void setPrice(double amount){
        this.price = amount;
    }
}

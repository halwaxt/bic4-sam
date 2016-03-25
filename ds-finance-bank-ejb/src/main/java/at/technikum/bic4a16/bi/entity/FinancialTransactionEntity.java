package at.technikum.bic4a16.bi.entity;

/**
 *
 * @author Romeo
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import at.technikum.bic4a16.bi.model.*;

import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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

    private int timestamp;
    private int numberOfShares;
    private State state;
    private double price;
    private Action action;

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


    public int getTimestamp(){
        return timestamp;
    }
    public void setTimestamp(int timestamp){
        this.timestamp = timestamp;
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
        return this.action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public State getState(){
        return state;
    }
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

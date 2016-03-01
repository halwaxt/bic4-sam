package at.technikum.bic4a16.bi.entity;

/**
 *
 * @author Romeo
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="TRANSACTION")
public class FinancialTransactionEntity implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(name="CUSTOMER_FK")
    private CustomerEntity customer;
    @Id
    @ManyToOne
    @JoinColumn(name="COMPANY_FK")
    private CompanyEntity company;
    @Id
    private int timestamp;
    private long numberofshares;
    private String state;
    private BigDecimal amount;
      
    public CustomerEntity getCustomer(){
        return customer;
    }
    public void setCustomer(CustomerEntity customer){
        this.customer = customer;
    }
    public CompanyEntity getCompany(){
        return company;
    }
    public void setCompany(CompanyEntity company){
        this.company = company;
    } 
    public int getTimestamp(){
        return timestamp;
    }
    public void setTimestamp(int timestamp){
        this.timestamp = timestamp;
    }
    public long getNumberOfShares(){
        return numberofshares;
    }
    public void setNumberOfShares(long numberofshares){
        this.numberofshares = numberofshares;
    }
    public String getState(){
        return state;
    }
    public void setState(String state){
        this.state = state;
    }
    public BigDecimal getAmount(){
        return amount;
    }
    public void setAmount(BigDecimal amount){
        this.amount = amount;
    }
}

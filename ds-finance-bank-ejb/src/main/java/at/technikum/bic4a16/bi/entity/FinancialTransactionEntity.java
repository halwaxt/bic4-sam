package at.technikum.bic4a16.bi.entity;

/**
 *
 * @author Romeo
 */

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="TRANSACTION")
public class FinancialTransactionEntity implements Serializable{
    @Id
    int customer;
    @Id
    String symbol;
    @Id
    int timestamp;
    long numberofshares;
    String state;
    BigDecimal amount;
      
    public int getCustomer(){
        return customer;
    }
    public void setCustomer(int customer){
        this.customer = customer;
    }
    public String getSymbol(){
        return symbol;
    }
    public void setSymbol(String symbol){
        this.symbol = symbol;
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

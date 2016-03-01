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
@Table(name="COMPANY")
public class CompanyEntity implements Serializable{
    @Id
    String symbol;
    String name;
    BigDecimal lasttradingprice;
    long floatshares;
    String stockexchange;
    
    public String getSymbol(){
        return symbol;
    }
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public BigDecimal getLastTradingPrice(){
        return lasttradingprice;
    }
    public void setLastTradingPrice(BigDecimal lasttradingprice){
        this.lasttradingprice = lasttradingprice;
    }
    public long getfloatShares(){
        return floatshares;
    }
    public void setfloatShares(long floatshares){
        this.floatshares = floatshares;
    }  
    public String getStockExchange(){
        return stockexchange;
    }
    public void setStockExchange(String stockexchange){
        this.stockexchange = stockexchange;
    }
}
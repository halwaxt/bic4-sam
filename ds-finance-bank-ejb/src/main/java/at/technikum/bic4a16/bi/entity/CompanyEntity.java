package at.technikum.bic4a16.bi.entity;

/**
 *
 * @author Romeo
 */

import at.technikum.bic4a16.bi.model.Company;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="COMPANY")
public class CompanyEntity implements Company, Serializable{
    @Id
    private String symbol;
    private String name;
    private BigDecimal lastTradingPrice;
    private long floatShares;
    private String stockExchange;
    
    @OneToMany(mappedBy="company")
    private List<FinancialTransactionEntity> transactions;


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSymbol() {
        return this.symbol;
    }

    @Override
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public BigDecimal getLastTradingPrice() {
        return this.lastTradingPrice;
    }

    @Override
    public void setLastTradingPrice(BigDecimal lastTradingPrice) {
        this.lastTradingPrice = lastTradingPrice;
    }

    @Override
    public long getFloatShares() {
        return this.floatShares;
    }

    @Override
    public void setFloatShares(long floatShares) {
        this.floatShares = floatShares;
    }

    @Override
    public String getStockExchange() {
        return this.stockExchange;
    }

    @Override
    public void setStockExchange(String stockExchange) {
        this.stockExchange = stockExchange;
    }


}
package at.technikum.bic4a16.bi.model;

import java.math.BigDecimal;

public final class CompanyModel implements Company{

    private String name;
    private String symbol;
    private BigDecimal lastTradingPrice;
    private long floatShares;
    private String stockExchange;



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

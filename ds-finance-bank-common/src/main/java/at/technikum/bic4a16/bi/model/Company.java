package at.technikum.bic4a16.bi.model;

import java.math.BigDecimal;

/**
 * Created by Thomas on 24.02.16.
 */
public interface Company {
    // name - e.g. APPLE INC
    String getName();
    void setName(String name);
    
    // symbol, which is an unique identifier - e.g. AAPL
    String getSymbol();
    void setSymbol(String symbol);
    
    // last trading price - e.g. 96.76
    BigDecimal getLastTradingPrice();
    void setLastTradingPrice(BigDecimal lastTradingPrice);
    
    // last float shares - e.g. 5540480000
    long getFloatShares();
    void setFloatShares(long floatShares);
    
    // stock exchange, name of the stock the company belongs to - e.g. NMS
    String getStockExchange();
    void setStockExchange(String stockExchange);
}

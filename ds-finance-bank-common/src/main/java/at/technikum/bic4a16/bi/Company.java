package at.technikum.bic4a16.bi;

import java.math.BigDecimal;

/**
 * Created by Thomas on 24.02.16.
 */
public interface Company {
    String getName();
    String getSymbol();
    BigDecimal getLastTradingPrice();
    long getFloatShares();
    String getStockExchange();
}

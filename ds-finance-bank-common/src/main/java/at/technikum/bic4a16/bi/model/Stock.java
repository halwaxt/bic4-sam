package at.technikum.bic4a16.bi.model;

import java.math.BigDecimal;

/**
 * Created by Thomas on 25.02.16.
 */

/* this is a calculated object which will never be persisted */

public interface Stock {
    // company which corresponds to the stock - e.g. object for company with symbol AAPL    
    Company getCompany();

    // number of shares - e.g. 100
    long getNumberOfShares();

    // current value of the shares - e.g. 500 (numberOfShares * company.getLastTradingPrice())
    BigDecimal getCurrentValue();
}

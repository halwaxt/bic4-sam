package at.technikum.bic4a16.bi.model;

import java.math.BigDecimal;

/**
 * Created by Thomas on 25.02.16.
 */

/* this is a calculated object which will never be persisted */

public interface Stock {
    Company getCompany();
    long getNumberOfShares();
    BigDecimal getCurrentValue();
}

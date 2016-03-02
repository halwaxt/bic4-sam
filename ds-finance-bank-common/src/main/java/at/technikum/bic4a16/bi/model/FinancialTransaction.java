package at.technikum.bic4a16.bi.model;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by Thomas on 24.02.16.
 */
public interface FinancialTransaction {
    FinancialTransactionRequest getRequest();

    State getState();

    UUID getId();

    BigDecimal getAmount();
}

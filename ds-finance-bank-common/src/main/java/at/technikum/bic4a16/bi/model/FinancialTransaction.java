package at.technikum.bic4a16.bi.model;

import javax.ejb.Remote;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Created by Thomas on 24.02.16.
 */

@Remote
public interface FinancialTransaction extends Serializable {
    FinancialTransactionRequest getRequest();

    State getState();

    UUID getId();

    BigDecimal getAmount();
}

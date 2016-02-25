package at.technikum.bic4a16.bi;

import java.util.UUID;

/**
 * Created by Thomas on 24.02.16.
 */
public interface Bankster {
    FinancialTransaction executeTransaction(FinancialTransactionRequest request);
}

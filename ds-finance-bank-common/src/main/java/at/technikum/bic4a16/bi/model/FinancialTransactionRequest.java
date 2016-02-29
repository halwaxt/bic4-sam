package at.technikum.bic4a16.bi.model;

/**
 * Created by Thomas on 25.02.16.
 */
public interface FinancialTransactionRequest {
    Company getCompany();

    Customer getCustomer();

    long getNumberOfShares();
    Action getAction();
}

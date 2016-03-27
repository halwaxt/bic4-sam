package at.technikum.bic4a16.bi.model;

import javax.ejb.Remote;
import java.io.Serializable;

/**
 * Created by Thomas on 25.02.16.
 */

@Remote
public interface FinancialTransactionRequest extends Serializable{
    Company getCompany();

    Customer getCustomer();

    int getNumberOfShares();
    Action getAction();
}

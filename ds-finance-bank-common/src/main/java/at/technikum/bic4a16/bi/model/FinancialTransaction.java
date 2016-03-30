package at.technikum.bic4a16.bi.model;

import javax.ejb.Remote;
import java.io.Serializable;

/**
 * Created by Thomas on 24.02.16.
 */

@Remote
public interface FinancialTransaction extends Serializable {
    Company getCompany();
    Customer getCustomer();
    int getNumberOfShares();
    Action getAction();
    int getId();
    double getPrice();
}

package at.technikum.bic4a16.bi.entity;

import at.technikum.bic4a16.bi.model.Action;
import at.technikum.bic4a16.bi.model.Company;
import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.FinancialTransactionRequest;

import java.io.Serializable;

public class FinancialTransactionRequestEntity implements FinancialTransactionRequest, Serializable {




    @Override
    public Company getCompany() {
        return null;
    }

    @Override
    public Customer getCustomer() {
        return null;
    }

    @Override
    public int getNumberOfShares() {
        return 0;
    }

    @Override
    public Action getAction() {
        return null;
    }
}

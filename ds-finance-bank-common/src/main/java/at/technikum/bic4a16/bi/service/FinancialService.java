package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.*;

import javax.ejb.Remote;

@Remote
public interface FinancialService {
    FinancialTransaction submitTransaction(FinancialTransactionRequest request);
    FinancialTransactionRequest createRequest(Customer customer, Company company, int shares, Action action);
    int getVersion();
}

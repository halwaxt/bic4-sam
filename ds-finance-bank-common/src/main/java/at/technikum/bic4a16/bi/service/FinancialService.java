package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.*;

import javax.ejb.Local;
import javax.ejb.Remote;
import java.util.UUID;

@Local
public interface FinancialService {
    FinancialTransaction submitTransaction(FinancialTransactionRequest request);
    FinancialTransactionRequest createRequest(Customer customer, Company company, long shares, Action action);
}

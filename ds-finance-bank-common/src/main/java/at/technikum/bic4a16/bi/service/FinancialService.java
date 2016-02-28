package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.FinancialTransaction;
import at.technikum.bic4a16.bi.model.FinancialTransactionRequest;

import javax.ejb.Stateless;

import java.util.UUID;

/**
 * Created by Thomas on 24.02.16.
 */
@Stateless
public interface FinancialService {
    FinancialTransaction executeTransaction(FinancialTransactionRequest request);
}

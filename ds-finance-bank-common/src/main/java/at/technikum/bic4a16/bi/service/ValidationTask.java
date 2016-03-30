package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.FinancialTransaction;

public interface ValidationTask {
    boolean isValid(FinancialTransaction financialTransaction);
}

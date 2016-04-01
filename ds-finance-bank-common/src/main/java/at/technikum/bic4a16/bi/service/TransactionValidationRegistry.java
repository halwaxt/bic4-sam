package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.model.FinancialTransaction;

public interface TransactionValidationRegistry {
    void register(ValidationTask validationTask);
    boolean isValid(FinancialTransaction transaction);
}

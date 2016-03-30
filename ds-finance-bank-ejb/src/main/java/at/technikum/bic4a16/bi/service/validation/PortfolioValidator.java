package at.technikum.bic4a16.bi.service.validation;

import at.technikum.bic4a16.bi.model.FinancialTransaction;
import at.technikum.bic4a16.bi.service.TransactionValidationRegistry;
import at.technikum.bic4a16.bi.service.ValidationTask;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Singleton
@Startup
public class PortfolioValidator implements ValidationTask {

    @Inject
    public PortfolioValidator(TransactionValidationRegistry validationRegistry) {
        validationRegistry.register(this);
    }

    @Override
    public boolean isValid(FinancialTransaction financialTransaction) {
        return true;
    }
}

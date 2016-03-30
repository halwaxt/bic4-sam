package at.technikum.bic4a16.bi.service.validation;

import at.technikum.bic4a16.bi.model.FinancialTransaction;
import at.technikum.bic4a16.bi.service.TransactionValidationRegistry;
import at.technikum.bic4a16.bi.service.ValidationTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.security.PermitAll;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.HashSet;
import java.util.Set;

@Singleton
@Startup
@PermitAll
public class DefaultValidationRegistry implements TransactionValidationRegistry {
    private static final Logger LOG = LoggerFactory.getLogger(DefaultValidationRegistry.class);

    Set<ValidationTask> verificationTasks = new HashSet<>();

    @Override
    public void register(ValidationTask validationTask) {

        verificationTasks.add(validationTask);
        LOG.info("added validation task " + validationTask);
    }

    @Override
    public boolean isValid(FinancialTransaction transaction) {
        for (ValidationTask task : verificationTasks) {
            final boolean ok = task.isValid(transaction);
            LOG.info(task +  " validates transaction to " + ok);
            if (! ok) return false;
        }
        return true;
    }
}

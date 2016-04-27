package at.technikum.bic4a16.bi.service.validation;

import at.technikum.bic4a16.bi.dao.FinancialTransactionDAO;
import at.technikum.bic4a16.bi.entity.FinancialTransactionEntity;
import at.technikum.bic4a16.bi.model.Action;
import at.technikum.bic4a16.bi.model.FinancialTransaction;
import at.technikum.bic4a16.bi.model.State;
import at.technikum.bic4a16.bi.model.Stock;
import at.technikum.bic4a16.bi.service.TransactionValidationRegistry;
import at.technikum.bic4a16.bi.service.ValidationTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

@Singleton
@Startup
public class BankLimitValidator implements ValidationTask {
    private static final Logger LOG = LoggerFactory.getLogger(BankLimitValidator.class);
    private static final BigDecimal BANKLIMIT = new BigDecimal(1000 * 1000 * 1000);

    @EJB
    FinancialTransactionDAO financialTransactionDAO;

    public BankLimitValidator() {
    }

    @Inject
    public BankLimitValidator(TransactionValidationRegistry validationRegistry) {
        LOG.info("registering at " + validationRegistry.toString());
        validationRegistry.register(this);
    }

    @Override
    public boolean isValid(FinancialTransaction financialTransaction) {

        if (financialTransaction.getAction() == Action.SELL) {
            return true;
        }

        final List<Stock> allOwnedShares = financialTransactionDAO.getPortfolio();
        final BigDecimal valueOfAllOwnedShares = allOwnedShares.stream().map(Stock::getCurrentValue).reduce(BigDecimal.ZERO, BigDecimal::add);
        LOG.info("bank owns shares with an overall value of " + valueOfAllOwnedShares);

        final BigDecimal valueOfCurrentTransaction = financialTransaction.getCompany().getLastTradingPrice().multiply(new BigDecimal(financialTransaction.getNumberOfShares()));
        final BigDecimal remainingValueForTransactions = BANKLIMIT.subtract(valueOfAllOwnedShares).subtract(valueOfCurrentTransaction);
        LOG.info("bank can spend another " + remainingValueForTransactions);

        if (remainingValueForTransactions.compareTo(BigDecimal.ZERO) > 0) {
            return true;
        }
        else {
            FinancialTransactionEntity entity = (FinancialTransactionEntity)financialTransaction;
            entity.setMessage("Bank limit exceeded.");
            entity.setState(State.DECLINED);
            return false;
        }
    }
}

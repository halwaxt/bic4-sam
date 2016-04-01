package at.technikum.bic4a16.bi.service.validation;

import at.technikum.bic4a16.bi.dao.FinancialTransactionDAO;
import at.technikum.bic4a16.bi.entity.CustomerEntity;
import at.technikum.bic4a16.bi.entity.FinancialTransactionEntity;
import at.technikum.bic4a16.bi.entity.StockEntity;
import at.technikum.bic4a16.bi.model.Action;
import at.technikum.bic4a16.bi.model.FinancialTransaction;
import at.technikum.bic4a16.bi.model.State;
import at.technikum.bic4a16.bi.model.Stock;
import at.technikum.bic4a16.bi.service.TransactionValidationRegistry;
import at.technikum.bic4a16.bi.service.ValidationTask;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Singleton
@Startup
public class StockOwnerValidator implements ValidationTask {

    @EJB
    FinancialTransactionDAO financialTransactionDAO;

    @Inject
    public StockOwnerValidator(TransactionValidationRegistry validationRegistry) {
        validationRegistry.register(this);
    }

    @Override
    public boolean isValid(FinancialTransaction financialTransaction) {

        // do not care about buying
        if (financialTransaction.getAction() == Action.BUY) return true;

        final List<Stock> portfolio = financialTransactionDAO.getPortfolio((CustomerEntity) financialTransaction.getCustomer());
        FinancialTransactionEntity entity = (FinancialTransactionEntity)financialTransaction;

        if (portfolio.size() == 0) {
            entity.setMessage("Customer does not own any shares.");
            entity.setState(State.DECLINED);
            return false;
        }

        final Optional<Stock> ownedStock = portfolio.stream().filter(stock -> stock.getCompany().equals(financialTransaction.getCompany())).findFirst();
        if (! ownedStock.isPresent()) {
            entity.setMessage("Customer does not own the share.");
            entity.setState(State.DECLINED);
            return false;
        }

        if (ownedStock.get().getNumberOfShares() < financialTransaction.getNumberOfShares()) {
            entity.setMessage("Customer does not own desired number of shares.");
            entity.setState(State.DECLINED);
            return true;
        }

        return true;
    }
}

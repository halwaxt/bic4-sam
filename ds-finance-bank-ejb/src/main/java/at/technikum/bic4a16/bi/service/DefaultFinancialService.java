package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.dao.FinancialTransactionDAO;
import at.technikum.bic4a16.bi.entity.CompanyEntity;
import at.technikum.bic4a16.bi.entity.CustomerEntity;
import at.technikum.bic4a16.bi.entity.FinancialTransactionEntity;
import at.technikum.bic4a16.bi.model.*;
import net.froihofer.dsfinance.ws.trading.TradingClientFactory;
import net.froihofer.dsfinance.ws.trading.TradingWSException_Exception;
import net.froihofer.dsfinance.ws.trading.TradingWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.concurrent.ManagedExecutorService;
import java.time.LocalDateTime;

@Stateless
@PermitAll
public class DefaultFinancialService implements FinancialService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultFinancialService.class);

    @Resource
    ManagedExecutorService managedExecutorService;

    @EJB
    FinancialTransactionDAO financialTransactionDAO;

    @Override
    public int getVersion() { return 1;}

    @Override
    public FinancialTransaction submitTransaction(FinancialTransactionRequest request) {
        FinancialTransactionEntity financialTransaction = toPendingFinancialTransactionEntity(request);
        financialTransactionDAO.save(financialTransaction);
        managedExecutorService.execute(stockExchangeTransaction(financialTransaction));
        LOG.info("submitted a transaction for request " + request);
        return financialTransaction;
    }

    @Override
    public FinancialTransactionRequest createRequest(Customer customer, Company company, int shares, Action action) {
        DefaultFinancialTransactionRequest request = new DefaultFinancialTransactionRequest();
        request.setCustomer(customer);
        request.setCompany(company);
        request.setNumberOfShares(shares);
        request.setAction(action);
        return request;
    }

    private FinancialTransactionEntity toPendingFinancialTransactionEntity(FinancialTransactionRequest request) {
        FinancialTransactionEntity financialTransaction = new FinancialTransactionEntity();
        financialTransaction.setCustomer((CustomerEntity)request.getCustomer());
        financialTransaction.setCompany((CompanyEntity)request.getCompany());
        financialTransaction.setAction(request.getAction());
        financialTransaction.setNumberOfShares(request.getNumberOfShares());
        financialTransaction.setState(State.PENDING);
        financialTransaction.setDate(LocalDateTime.now());
        return financialTransaction;
    }

    private Runnable stockExchangeTransaction(FinancialTransactionEntity transaction) {
        return new Runnable() {
            @Override
            public void run() {
                LOG.info("executing financial transaction ...");

                try {
                    final TradingWebService tradingWebService = TradingClientFactory.createClient();
                    double transactionPrice = 0;
                    switch (transaction.getAction()) {
                        case SELL: {
                            LOG.info("selling ...");
                            transactionPrice = tradingWebService.sell(
                                    transaction.getCompany().getSymbol(),
                                    transaction.getNumberOfShares()
                            );
                            break;
                        }
                        case BUY: {
                            LOG.info("buying ...");
                            transactionPrice = tradingWebService.buy(
                                    transaction.getCompany().getSymbol(),
                                    transaction.getNumberOfShares()
                            );
                            break;
                        }
                    }
                    transaction.setState(State.COMPLETED);
                    transaction.setPrice(transactionPrice);

                } catch (TradingWSException_Exception e) {
                    transaction.setState(State.FAILED);
                    LOG.error("failed to execute financial transaction.", e);
                }
                finally {
                    LOG.info("persisting changed financial transaction");
                    financialTransactionDAO.update(transaction);
                }
            }
        };
    }
}

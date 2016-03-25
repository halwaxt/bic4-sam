package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.dao.FinancialTransactionDAO;
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

@Stateless
@PermitAll
public class DefaultFinancialService implements FinancialService {


    private static final Logger LOG = LoggerFactory.getLogger(DefaultFinancialService.class);

    @Resource
    ManagedExecutorService managedExecutorService;

    @EJB
    ModelMapper modelMapper;

    @EJB
    FinancialTransactionDAO financialTransactionDAO;

    @Override
    public int getVersion() { return 1;}

    @Override
    public FinancialTransaction submitTransaction(FinancialTransactionRequest request) {



        DefaultFinancialTransaction financialTransaction = new DefaultFinancialTransaction();
        financialTransaction.setRequest(request);
        financialTransaction.setState(State.PENDING);

        financialTransactionDAO.persist(modelMapper.toFinancialTransactionEntity(financialTransaction));

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

    private Runnable stockExchangeTransaction(DefaultFinancialTransaction transaction) {
        return new Runnable() {
            @Override
            public void run() {
                LOG.info("executing financial transaction ...");

                try {
                    final TradingWebService tradingWebService = TradingClientFactory.createClient();
                    double transactionPrice = 0;
                    switch (transaction.getRequest().getAction()) {
                        case SELL: {
                            LOG.info("selling ...");
                            transactionPrice = tradingWebService.sell(
                                    transaction.getRequest().getCompany().getSymbol(),
                                    transaction.getRequest().getNumberOfShares()
                            );
                            break;
                        }
                        case BUY: {
                            LOG.info("buying ...");
                            LOG.info("selling ...");
                            transactionPrice = tradingWebService.buy(
                                    transaction.getRequest().getCompany().getSymbol(),
                                    transaction.getRequest().getNumberOfShares()
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
                }


            }
        };
    }
}

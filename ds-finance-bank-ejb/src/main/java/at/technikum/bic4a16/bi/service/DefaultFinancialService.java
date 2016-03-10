package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.dao.CompanyEntityDAO;
import at.technikum.bic4a16.bi.entity.CompanyEntity;
import at.technikum.bic4a16.bi.model.*;
import net.froihofer.dsfinance.ws.trading.PublicStockQuote;
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
import java.util.List;
import java.util.function.Consumer;

@Stateless
@PermitAll
public class DefaultFinancialService implements FinancialService {


    private static final Logger LOG = LoggerFactory.getLogger(DefaultFinancialService.class);

    @Resource
    ManagedExecutorService managedExecutorService;


    @EJB
    CompanyEntityDAO companyEntityDAO;

    @Override
    public int getVersion() { return 1;}

    @Override
    public FinancialTransaction submitTransaction(FinancialTransactionRequest request) {

        LOG.debug("submitting a transaction fro request " + request);

        DefaultFinancialTransaction financialTransaction = new DefaultFinancialTransaction();
        financialTransaction.setRequest(request);
        financialTransaction.setState(State.PENDING);


        final TradingWebService webService = TradingClientFactory.createClient();
        try {
            final List<PublicStockQuote> stockQuotesByCompanyName = webService.findStockQuotesByCompanyName("Apple");
            stockQuotesByCompanyName.forEach(new Consumer<PublicStockQuote>() {
                @Override
                public void accept(PublicStockQuote publicStockQuote) {
                    LOG.info("found stock " + publicStockQuote.getCompanyName());

                    CompanyEntity companyEntity;

                    companyEntity = companyEntityDAO.findBySymbol(publicStockQuote.getSymbol());
                    if (companyEntity == null) {
                        companyEntity = new CompanyEntity();
                        companyEntity.setName(publicStockQuote.getCompanyName());
                        companyEntity.setLastTradingPrice(publicStockQuote.getLastTradePrice());
                        companyEntity.setfloatShares(publicStockQuote.getFloatShares());
                        companyEntity.setSymbol(publicStockQuote.getSymbol());
                        companyEntity.setStockExchange(publicStockQuote.getStockExchange());

                        companyEntityDAO.persist(companyEntity);
                        LOG.info("create company entity: " + companyEntity.getName());
                    }
                    else {
                        companyEntity.setLastTradingPrice(publicStockQuote.getLastTradePrice());
                        companyEntity.setfloatShares(publicStockQuote.getFloatShares());
                        companyEntityDAO.merge(companyEntity);
                        LOG.info("updated company entity: " + companyEntity.getName());
                    }
                }
            });

        } catch (TradingWSException_Exception e) {
            LOG.error("failed to call findStockQuotesByCompanyName with param Apple", e);
        }


        // persist transaction first
        // using DAO

        //
        //managedExecutorService.execute(stockExchangeTransaction(null));


        return financialTransaction;
    }

    @Override
    public FinancialTransactionRequest createRequest(Customer customer, Company company, long shares, Action action) {
        DefaultFinancialTransactionRequest request = new DefaultFinancialTransactionRequest();
        request.setCustomer(customer);
        request.setCompany(company);
        request.setNumberOfShares(shares);
        request.setAction(action);
        //logger.fine("created financial transaction request");
        return request;
    }

    private Runnable stockExchangeTransaction(FinancialTransaction transaction) {
        return new Runnable() {
            @Override
            public void run() {
                // verify prerequisites
                // call stock exchange and perform a buy or sell operation
                // update and persist state
                // update and persist portfolio
                // update and persist bank balance
            }
        };
    }
}

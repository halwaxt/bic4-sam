package at.technikum.bic4a16.bi.service;

import at.technikum.bic4a16.bi.dao.CompanyEntityDAO;
import at.technikum.bic4a16.bi.entity.CompanyEntity;
import net.froihofer.dsfinance.ws.trading.PublicStockQuote;
import net.froihofer.dsfinance.ws.trading.TradingClientFactory;
import net.froihofer.dsfinance.ws.trading.TradingWSException_Exception;
import net.froihofer.dsfinance.ws.trading.TradingWebService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Singleton
@Startup
public class StockGrabbingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(StockGrabbingService.class);
    private ScheduledFuture<?> scheduledFuture = null;


    @EJB
    CompanyEntityDAO companyEntityDAO;

    @Resource
    ManagedScheduledExecutorService scheduledExecutorService;

    @PostConstruct
    void startGrabbing() {

        LOGGER.info("grabbing stock quotes now using instance " + this.toString());

        if (scheduledFuture != null) {
            LOGGER.warn("grabbing is already scheduled.");
            return;
        }

        scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(grabAllStocks(), 5, 300, TimeUnit.SECONDS);
        LOGGER.info("created scheduled grabbing");
    }

    @PreDestroy
    void stopGrabbing() {
        if (scheduledFuture == null) {
            LOGGER.info("grabbing is not scheduled.");
            return;
        }

        final boolean cancelled = scheduledFuture.cancel(true);
        if (cancelled) {
            LOGGER.info("cancelled scheduled grabbing.");
        }
        else {
            LOGGER.warn("unable to cancel scheduled stock grabber.");
        }
    }


    private Runnable grabAllStocks() {
        return new Runnable() {

            final TradingWebService webService = TradingClientFactory.createClient();

            @Override
            public void run() {
                List<PublicStockQuote> stockQuotes;

                try {
                    stockQuotes = webService.findStockQuotesByCompanyName("%");
                    LOGGER.info("found stocks: " + stockQuotes.size());
                }
                catch (TradingWSException_Exception e) {
                    LOGGER.error("failed to grab all stocks.", e.getMessage());
                    return;
                }

                CompanyEntity companyEntity;
                for (PublicStockQuote quote : stockQuotes) {
                    if (quote.getCompanyName().startsWith("N/A")) continue;
                    if (quote.getCompanyName().startsWith("** SEE")) continue;

                    companyEntity = companyEntityDAO.findBySymbol(quote.getSymbol());
                    try {
                        if (companyEntity == null) {
                            companyEntityDAO.persist(insertableEntity(quote));
                            LOGGER.info("created company entity: " + quote.getSymbol());
                        }
                        else {
                            companyEntity.setLastTradingPrice(quote.getLastTradePrice());
                            companyEntity.setfloatShares(quote.getFloatShares());
                            companyEntityDAO.merge(companyEntity);
                            LOGGER.info("updated company entity: " + quote.getSymbol());
                        }
                    }
                    catch (Exception ex) {
                        LOGGER.error("error: ", ex.getMessage());
                    }
                }
            }
        };
    }


    private CompanyEntity insertableEntity(PublicStockQuote quote) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName(quote.getCompanyName());
        companyEntity.setLastTradingPrice(quote.getLastTradePrice());
        companyEntity.setfloatShares(quote.getFloatShares());
        companyEntity.setSymbol(quote.getSymbol());
        companyEntity.setStockExchange(quote.getStockExchange());
        return companyEntity;
    }


}

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
import java.util.ArrayList;
import java.util.Collection;
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

    @SuppressWarnings("EjbEnvironmentInspection")
    @Resource
    ManagedScheduledExecutorService scheduledExecutorService;

    @PostConstruct
    void startGrabbing() {

        LOGGER.info("grabbing stock quotes now using instance " + this.toString());

        if (scheduledFuture != null) {
            LOGGER.warn("grabbing is already scheduled.");
            return;
        }

        scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(grabAllStocks(), 5, 60, TimeUnit.SECONDS);
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
            @Override
            public void run() {
                List<PublicStockQuote> stockQuotes;

                try {
                    final TradingWebService webService = TradingClientFactory.createClient();
                    stockQuotes = webService.findStockQuotesByCompanyName("%");
                    LOGGER.info("found stocks: " + stockQuotes.size());
                }
                catch (TradingWSException_Exception e) {
                    LOGGER.error("failed to grab all stocks.", e.getMessage());
                    return;
                }

                List<CompanyEntity> entities = new ArrayList<>();
                for (PublicStockQuote quote : stockQuotes) {
                    if (quote.getCompanyName().startsWith("N/A")) continue;
                    if (quote.getCompanyName().startsWith("** SEE")) continue;

                    entities.add(toCompanyEntity(quote));
                }

                LOGGER.info("saved companies: " + companyEntityDAO.bulkSave(entities).size());
            }
        };
    }


    private CompanyEntity toCompanyEntity(PublicStockQuote quote) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName(quote.getCompanyName());
        companyEntity.setLastTradingPrice(quote.getLastTradePrice());
        companyEntity.setSymbol(quote.getSymbol());

        if (quote.getFloatShares() != null) {
            companyEntity.setFloatShares(quote.getFloatShares());
        }

        if (quote.getStockExchange() != null) {
            companyEntity.setStockExchange(quote.getStockExchange());
        }
        return companyEntity;
    }


}

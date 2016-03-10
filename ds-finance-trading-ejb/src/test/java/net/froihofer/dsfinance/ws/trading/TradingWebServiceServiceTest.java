package net.froihofer.dsfinance.ws.trading;

import org.junit.Assert;

import javax.xml.ws.Binding;
import javax.xml.ws.BindingProvider;
import java.util.List;

import static org.junit.Assert.*;

public class TradingWebServiceServiceTest {

    @org.junit.Test
    public void testGetTradingWebServicePort() throws Exception {
        TradingWebServiceService service = new TradingWebServiceService();

        final TradingWebService tradingWebService = service.getTradingWebServicePort();
        BindingProvider bindingProvider = (BindingProvider)tradingWebService;
        bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "bic4a16_03");
        bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "uum4quaeCh");


        final List<PublicStockQuote> stockQuotesByCompanyName = tradingWebService.findStockQuotesByCompanyName("Apple");
        Assert.assertEquals(1, stockQuotesByCompanyName.size());
        Assert.assertEquals("AAPL", stockQuotesByCompanyName.get(0).getSymbol());
    }
}

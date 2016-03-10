package net.froihofer.dsfinance.ws.trading;

import javax.xml.ws.BindingProvider;

public final class TradingClientFactory {
    private TradingClientFactory()
    {}

    public static TradingWebService createClient() {
        TradingWebServiceService service = new TradingWebServiceService();

        final TradingWebService tradingWebService = service.getTradingWebServicePort();
        BindingProvider bindingProvider = (BindingProvider)tradingWebService;
        bindingProvider.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, "bic4a16_03");
        bindingProvider.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, "uum4quaeCh");

        return tradingWebService;
    }
}

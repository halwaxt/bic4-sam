package at.technikum.bic4a16.servlets;

import at.technikum.bic4a16.bi.model.*;
import at.technikum.bic4a16.bi.service.AuthenticationService;
import at.technikum.bic4a16.bi.service.CompanyService;
import at.technikum.bic4a16.bi.service.CustomerService;
import at.technikum.bic4a16.bi.service.FinancialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import com.google.gson.*;
import java.util.*;

@SessionScoped
    public class TestServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(TestServlet.class);

    @EJB
    FinancialService financialService;

    @EJB
    AuthenticationService authenticationService;

    @EJB
    CustomerService customerService;

    @EJB
    CompanyService companyService;

    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doOptions(request,  response);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, X-Requested-With");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        LOG.info("http GET method invoked");

        Customer customer;
        final Customer[] halwaxes = customerService.getCustomer("HALWAX");
        if (halwaxes == null || halwaxes.length == 0) {
            customer = customerService.createCustomer("HALWAX");
        }
        else {
            customer = halwaxes[0];
            LOG.info("using existing halwax :-)");
        }

        final Company apple = companyService.getCompany("AAPL");
        final Company adobe = companyService.getCompany("ADBE");
        final Company extremeNetworks = companyService.getCompany("EXTR");

        /*
        final FinancialTransactionRequest buyAppleRequest = financialService.createRequest(customer, apple, 7, Action.BUY);
        final FinancialTransaction buyAppleTransaction = financialService.submitTransaction(buyAppleRequest);

        final FinancialTransactionRequest sellAppleRequest = financialService.createRequest(customer, apple, 5, Action.SELL);
        final FinancialTransaction sellAppleT = financialService.submitTransaction(sellAppleRequest);
        */
        final FinancialTransactionRequest buyAdobeRequest = financialService.createRequest(customer, adobe, 10, Action.BUY);
        final FinancialTransaction buyAdobeT = financialService.submitTransaction(buyAdobeRequest);
/*
        final FinancialTransactionRequest sellAdobeRequest = financialService.createRequest(customer, adobe, 10, Action.SELL);
        final FinancialTransaction financialTransaction = financialService.submitTransaction(sellAdobeRequest);

        final FinancialTransactionRequest buyExtremeRequest = financialService.createRequest(customer, extremeNetworks, 12, Action.BUY);
        final FinancialTransaction buyExtremeT = financialService.submitTransaction(buyExtremeRequest);
*/

        final Stock[] portfolio = customerService.getPortfolio(customer);
        LOG.info("customer owns stocks: " + portfolio.length);
        out.print("schau ma mal obs da geht");

    }
}

package at.technikum.bic4a16.servlets;

import at.technikum.bic4a16.bi.model.Action;
import at.technikum.bic4a16.bi.model.Company;
import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.FinancialTransactionRequest;
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

@SessionScoped
public class TestServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(TestServlet.class);

    @EJB
    FinancialService financialService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOG.info("http GET method invoked");

        final PrintWriter writer = response.getWriter();


        final FinancialTransactionRequest transactionRequest = financialService.createRequest(
                new Customer() {
                    @Override
                    public int getId() {
                        return 4711;
                    }

                    @Override
                    public String getName() {
                        return "Thomas";
                    }
                },
                new Company() {
                    @Override
                    public String getName() {
                        return "Apple Inc.";
                    }

                    @Override
                    public void setName(String name) {

                    }

                    @Override
                    public String getSymbol() {
                        return "AAPL";
                    }

                    @Override
                    public void setSymbol(String symbol) {

                    }

                    @Override
                    public BigDecimal getLastTradingPrice() {
                        return new BigDecimal(9887.123);
                    }

                    @Override
                    public void setLastTradingPrice(BigDecimal lastTradingPrice) {

                    }

                    @Override
                    public long getFloatShares() {
                        return 39327892;
                    }

                    @Override
                    public void setFloatShares(long floatShares) {

                    }

                    @Override
                    public String getStockExchange() {
                        return "NYSE";
                    }

                    @Override
                    public void setStockExchange(String stockExchange) {

                    }
                },
                7,
                Action.BUY
        );

        writer.write(transactionRequest.toString());


        //Integer version = financialService.getVersion();

        //writer.write(version.toString());



        writer.close();

    }
}

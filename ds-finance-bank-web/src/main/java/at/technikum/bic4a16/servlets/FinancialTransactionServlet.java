package at.technikum.bic4a16.servlets;

import at.technikum.bic4a16.bi.model.*;
import at.technikum.bic4a16.bi.service.CompanyService;
import at.technikum.bic4a16.bi.service.CustomerService;
import at.technikum.bic4a16.bi.service.FinancialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Patrik on 22.03.2016.
 */
@SessionScoped
public class FinancialTransactionServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(FinancialTransactionServlet.class);


    @EJB
    FinancialService financialService;

    @EJB
    CustomerService customerService;

    @EJB
    CompanyService companyService;

    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doOptions(request,  response);
        ResponseHelper.SetAccessControlHeaders(response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ResponseHelper.SetAccessControlHeaders(response);

        LOG.info("Action=" + request.getParameter("action"));
        LOG.info("Numbers=" + request.getParameter("numbers"));
        LOG.info("Symbol=" + request.getParameter("symbol"));

        String actionString = request.getParameter("action").toUpperCase();
        Action action;
        String symbol = request.getParameter("symbol");

        int shares;
        try {
            shares = Integer.parseInt(request.getParameter("numbers"));
        }
        catch (NumberFormatException nfEx) {
            LOG.warn("numbers param is not an number.", nfEx);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if("BUY".equals(actionString)) {
            action = Action.BUY;
        }
        else if ("SELL".equals(actionString)) {
            action = Action.SELL;
        }
        else {
            LOG.warn("unknown action " + actionString);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Customer customer = customerService.getCustomer(Integer.parseInt(request.getParameter("ID")));

        LOG.info("using customer " + customer.getName());
        Company company = companyService.getCompany(symbol);
        FinancialTransactionRequest financialTransactionRequest = financialService.createRequest(customer, company, shares, action);
        FinancialTransaction financialTransaction = financialService.submitTransaction(financialTransactionRequest);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(out, financialTransaction);

        out.flush();
        out.close();
    }
}

package at.technikum.bic4a16.servlets;

import at.technikum.bic4a16.bi.model.*;
import at.technikum.bic4a16.bi.service.CompanyService;
import at.technikum.bic4a16.bi.service.CustomerService;
import at.technikum.bic4a16.bi.service.FinancialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
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
import java.util.ArrayList;

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
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, X-Requested-With");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOG.info("Action=" + request.getParameter("action"));
        LOG.info("Numbers=" + request.getParameter("numbers"));
        LOG.info("Symbol=" + request.getParameter("symbol"));

        String actionString = request.getParameter("action");
        Action action;
        String symbol = request.getParameter("symbol");
        int shares = Integer.parseInt(request.getParameter("numbers"));

        if(actionString.equals("BUY")) {
            action = Action.BUY;
        }
        else {
            action = Action.SELL;
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

        response.addHeader("Access-Control-Allow-Origin", "*");
        out.close();

    }
}

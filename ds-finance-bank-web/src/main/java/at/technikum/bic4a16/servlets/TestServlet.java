package at.technikum.bic4a16.servlets;

import at.technikum.bic4a16.bi.model.Action;
import at.technikum.bic4a16.bi.model.Company;
import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.FinancialTransactionRequest;
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


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        LOG.info("http GET method invoked");
        LOG.info("checking injected interface implementations ...");
        LOG.info("AuthenticationService: " + (authenticationService == null ? "ABSENT" : "PRESENT"));
        LOG.info("CustomerService: " + (customerService == null ? "ABSENT" : "PRESENT"));
        LOG.info("CompanyService: " + (companyService == null ? "ABSENT" : "PRESENT"));
        LOG.info("FinancialService: " + (financialService == null ? "ABSENT" : "PRESENT"));



    }
}

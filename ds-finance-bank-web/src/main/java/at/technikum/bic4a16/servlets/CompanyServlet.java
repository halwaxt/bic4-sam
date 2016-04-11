package at.technikum.bic4a16.servlets;

import at.technikum.bic4a16.bi.model.Action;
import at.technikum.bic4a16.bi.model.Company;
import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.FinancialTransactionRequest;
import at.technikum.bic4a16.bi.service.AuthenticationService;
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
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Patrik on 22.03.2016.
 */
@SessionScoped
public class CompanyServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(CompanyServlet.class);

    @EJB
    CompanyService companyService;

    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doOptions(request,  response);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, X-Requested-With");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Company[] companies = companyService.getAllCompanies();

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(out, companies);

        out.flush();

        response.addHeader("Access-Control-Allow-Origin", "*");
        out.close();
    }
}

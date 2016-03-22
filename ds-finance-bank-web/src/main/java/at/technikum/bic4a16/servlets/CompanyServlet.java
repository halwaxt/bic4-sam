package at.technikum.bic4a16.servlets;

import at.technikum.bic4a16.bi.model.Action;
import at.technikum.bic4a16.bi.model.Company;
import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.FinancialTransactionRequest;
import at.technikum.bic4a16.bi.service.AuthenticationService;
import at.technikum.bic4a16.bi.service.CompanyService;
import at.technikum.bic4a16.bi.service.CustomerService;
import at.technikum.bic4a16.bi.service.FinancialService;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Company[] companies = companyService.getAllCompanies();

        Company c = companies[0];

        // System.out.println("NAME=" + companies[0].getName());

        final PrintWriter writer = response.getWriter();

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        ArrayList<JsonObject> list = new ArrayList<JsonObject>();

        JsonObject obj = new JsonObject();

        obj.addProperty("id", "2001");
        obj.addProperty("symbol", companies[0].getSymbol());
        obj.addProperty("name", companies[0].getName());
        obj.addProperty("lastTradingPrice", 50000);
        obj.addProperty("floatShares", 500);
        obj.addProperty("stockExchange", "NYSE");

        list.add(obj);

        obj = new JsonObject();

        obj.addProperty("id", "2002");
        obj.addProperty("symbol", "IBM");
        obj.addProperty("name", "IBM");
        obj.addProperty("lastTradingPrice", 40000);
        obj.addProperty("floatShares", 1000);
        obj.addProperty("stockExchange", "NYSE");

        list.add(obj);

        out.print(list);
        out.flush();

    }
}

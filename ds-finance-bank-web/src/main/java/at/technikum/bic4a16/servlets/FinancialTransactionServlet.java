package at.technikum.bic4a16.servlets;

import at.technikum.bic4a16.bi.service.CompanyService;
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
import java.util.ArrayList;

/**
 * Created by Patrik on 22.03.2016.
 */
@SessionScoped
public class FinancialTransactionServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(FinancialTransactionServlet.class);

    @EJB
    FinancialService financialService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Action=" + request.getParameter("action"));
        System.out.println("Numbers=" + request.getParameter("numbers"));

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        JsonObject obj = new JsonObject();

        out.print(obj);
        out.flush();

    }
}

package at.technikum.bic4a16.servlets;

import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.FinancialTransaction;
import at.technikum.bic4a16.bi.service.CustomerService;
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
 * Created by Michael
 */
@SessionScoped
public class CustomerTransactionServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerTransactionServlet.class);

    @EJB
    CustomerService customerService;

    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doOptions(request,  response);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, X-Requested-With");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("ID"));


        Customer customer = customerService.getCustomer(id);
        FinancialTransaction[] transactions = customerService.getTransactions(customer);

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        ObjectMapper objectMapper = new ObjectMapper();
        // objectMapper.writeValue(out, customer);
        objectMapper.writeValue(out, transactions);

        out.flush();

        response.addHeader("Access-Control-Allow-Origin", "*");
        out.close();
    }
}

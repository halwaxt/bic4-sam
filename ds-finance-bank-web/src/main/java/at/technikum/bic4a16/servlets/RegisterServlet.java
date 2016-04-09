package at.technikum.bic4a16.servlets;

import at.technikum.bic4a16.bi.entity.CustomerEntity;
import at.technikum.bic4a16.bi.model.*;
import at.technikum.bic4a16.bi.service.AuthenticationService;
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
 * Created by Michael on 28.03.2016.
 */
@SessionScoped
public class RegisterServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(RegisterServlet.class);

    @EJB
    AuthenticationService authenticationService;
    @EJB
    CustomerService customerService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String k=request.getParameter("kundenname");
        String n=request.getParameter("username");
        String p=request.getParameter("password");
        boolean e = new Boolean(request.getParameter("isEmployee")).booleanValue();
        ObjectMapper objectMapper = new ObjectMapper();



        try{

            Customer customer = customerService.createCustomer(k);
            if(n.length()==0){
                objectMapper.writeValue(out, customer);
            }
            else {
                User user = authenticationService.createUser(n, p, e, customer);
                objectMapper.writeValue(out, user);
            }


        }catch (Exception e2) {System.out.println(e2);}

        out.close();






    }
}

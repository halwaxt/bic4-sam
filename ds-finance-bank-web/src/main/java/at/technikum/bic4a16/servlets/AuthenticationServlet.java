package at.technikum.bic4a16.servlets;

import at.technikum.bic4a16.bi.model.Customer;
import at.technikum.bic4a16.bi.model.User;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Michael on 28.03.2016.
 */
@SessionScoped
public class AuthenticationServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationServlet.class);

    @EJB
    AuthenticationService authenticationService;
    @EJB
    CustomerService customerService;

    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doOptions(request,  response);
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type, X-Requested-With");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();

        String n = request.getParameter("username");
        String p = request.getParameter("password");

        try {
            if (authenticationService.authenticate(n, p) != null) {
                User user = authenticationService.authenticate(n, p);
                HttpSession session = request.getSession();
                String sessionId = session.getId();
                user.setSessionId(sessionId);
                objectMapper.writeValue(out, user);
            } else {
                response.setStatus(400);
            }
        } catch (Exception e2) {
            System.out.println(e2);
            response.setStatus(400);
        }

        response.addHeader("Access-Control-Allow-Origin", "*");
        out.close();
    }
}

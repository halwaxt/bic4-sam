package at.technikum.bic4a16.servlets;

import at.technikum.bic4a16.bi.model.Company;
import at.technikum.bic4a16.bi.service.CompanyService;
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
 * Created by Patrik on 27.03.2016.
 */
@SessionScoped
public class CreateUserServlet extends HttpServlet {
    private static final Logger LOG = LoggerFactory.getLogger(CreateUserServlet.class);

    @EJB
    UserService userService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}

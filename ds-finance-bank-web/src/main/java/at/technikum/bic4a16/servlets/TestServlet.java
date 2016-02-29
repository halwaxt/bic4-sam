package at.technikum.bic4a16.servlets;

import at.technikum.bic4a16.bi.service.FinancialService;

import javax.ejb.EJB;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "TestServlet", urlPatterns = {"/TestServlet"})
public class TestServlet extends HttpServlet {

    @Inject @Default
    private FinancialService financialService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final PrintWriter writer = response.getWriter();


        if (financialService == null) {
            writer.write("Kein Service injiziert");
        }
        else {
            writer.write("SOdalla, los gehts");
        }

        writer.close();

    }
}
